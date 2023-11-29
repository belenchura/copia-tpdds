package models.Entities.Ranking.Criterios;

import models.Entities.MonitoreoServicios.Entidad.Entidad;
import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.MonitoreoServicios.Prestacion;
import models.Entities.Ranking.DataRanking.Item;
import models.Entities.Ranking.DataRanking.ItemEntidad;
import models.Entities.Ranking.DataRanking.ResultEntidades;
import models.Entities.Ranking.TipoRanking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CantidadIncidentesPorEntidad implements TipoRanking {
  private List<Entidad> entidades;
  public CantidadIncidentesPorEntidad(List<Entidad> entidadesAOrdenar){
    this.entidades = entidadesAOrdenar;
  }
  public List<Item> generarRanking(List<Incidente> incidentes) {
    LocalDateTime fechaActual = LocalDateTime.now();
    Set<Prestacion> prestacionesReportadasEnSemana = new HashSet<>();

    List<Item> ranking = incidentes.stream()
        .filter(incidente -> esMismaSemana(incidente.getFechaYHoraApertura(), fechaActual)
            && incidente.getEstado()
            && prestacionesReportadasEnSemana.add(incidente.getPrestacion()))
        .collect(Collectors.groupingBy(
            incidente -> incidente.getPrestacion().getEstablecimiento().getEntidad(),
            Collectors.counting()
        ))
        .entrySet()
        .stream()
        .map(entry -> new Item(entry.getKey().getNombre(), entry.getValue().intValue()))
        .collect(Collectors.toList());

    return ordenarPorValorDescendente(ranking);
  }

  private Set<Prestacion> prestacionesReportadasEnUltimas24Horas(List<Incidente> incidentes) {
    Set<Prestacion> prestacionesReportadas = new HashSet<>();
    LocalDateTime fechaActual = LocalDateTime.now();

    for (Incidente incidente : incidentes) {
      if (esMismaSemana(incidente.getFechaYHoraApertura(), fechaActual)
          && incidente.getEstado()) {
        Prestacion prestacionActual = incidente.getPrestacion();

        // Verificar si la prestación ya fue reportada en las últimas 24 horas
        boolean prestacionYaReportada = prestacionesReportadas.contains(prestacionActual);

        // Si no fue reportada, la agregamos al conjunto
        if (!prestacionYaReportada) {
          prestacionesReportadas.add(prestacionActual);
        }
      }
    }

    return prestacionesReportadas;
  }
  private boolean esMismaSemana(LocalDateTime fechaIncidente, LocalDateTime fechaActual) {
    return fechaIncidente.toLocalDate().isEqual(fechaActual.toLocalDate());
  }

  private List<Item> ordenarPorValorDescendente(List<Item> items) {
    return items.stream()
        .sorted(Comparator.comparing(Item::getValor).reversed())
        .collect(Collectors.toList());
  }
}
