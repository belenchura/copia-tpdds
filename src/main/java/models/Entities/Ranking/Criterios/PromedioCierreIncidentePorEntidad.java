package models.Entities.Ranking.Criterios;
import models.Entities.MonitoreoServicios.Entidad.Entidad;
import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.Ranking.DataRanking.Item;
import models.Entities.Ranking.DataRanking.ItemEntidad;
import models.Entities.Ranking.DataRanking.ItemRanking;
import models.Entities.Ranking.DataRanking.ResultEntidades;
import models.Entities.Ranking.TipoRanking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class PromedioCierreIncidentePorEntidad implements TipoRanking {
  private List<Entidad> entidades;
  public PromedioCierreIncidentePorEntidad(List<Entidad> entidadesAOrdenar){
    this.entidades = entidadesAOrdenar;
  }
  public List<Item> generarRanking(List<Incidente> incidentesSemanales) {

    // Filtrar incidentes cerrados
    List<Incidente> incidentesCerrados = incidentesSemanales.stream()
        .filter(incidente -> !incidente.getEstado()) // estado false => incidente cerrado
        .collect(Collectors.toList());

    // Agrupar los incidentes cerrados por entidad
    Map<Entidad, List<Incidente>> incidentesCerradosPorEntidad = incidentesCerrados.stream()
        .collect(Collectors.groupingBy(incidente -> incidente.getPrestacion().getEstablecimiento().getEntidad()));


    // Calcular el promedio de diferencia de tiempo de cierre para cada entidad
    List<Item> itemsEntidad = incidentesCerradosPorEntidad.entrySet().stream()
        .map(entry -> {
          Entidad entidad = entry.getKey();
          List<Incidente> incidentesDeEntidad = entry.getValue();

          double promedioDiferenciaTiempoCierre = getPromedioDiferenciaTiempoCierre(incidentesDeEntidad);
          return new Item(entidad.getNombre(), promedioDiferenciaTiempoCierre);
        })
        .sorted(Comparator.comparingDouble(Item::getValor).reversed())
        .collect(Collectors.toList());

    return itemsEntidad;
  }

  private double getPromedioDiferenciaTiempoCierre(List<Incidente> incidentes) {
    return incidentes.stream()
        .filter(incidente -> !incidente.getEstado()) // incidentes cerrados
        .mapToDouble(incidente -> incidente.calcularDuracionCierreEnMinutos())
        .average()
        .orElse(0.0);
  }
}

