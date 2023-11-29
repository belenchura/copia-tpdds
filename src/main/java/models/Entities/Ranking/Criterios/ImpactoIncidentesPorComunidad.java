package models.Entities.Ranking.Criterios;

import models.Entities.MonitoreoServicios.Comunidad.Comunidad;
import models.Entities.MonitoreoServicios.Entidad.Entidad;
import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.Ranking.DataRanking.Item;
import models.Entities.Ranking.DataRanking.ItemComunidad;
import models.Entities.Ranking.DataRanking.ResultComunidades;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImpactoIncidentesPorComunidad {
  double coeficienteNoResueltos = 0.1;
  private List<Entidad> entidades;
  public ImpactoIncidentesPorComunidad(List<Entidad> entidadesAOrdenar){
    this.entidades = entidadesAOrdenar;
  }
  public List<Item> generarRanking(List<Incidente> incidentes) {
    // Agrupar incidentes por entidad
    Map<Entidad, List<Incidente>> incidentesPorEntidad = incidentes.stream()
        .collect(Collectors.groupingBy(incidente -> incidente.getPrestacion().getEstablecimiento().getEntidad()));

    // Calcular el impacto para cada entidad
    List<Item> ranking = incidentesPorEntidad.entrySet().stream()
        .map(entry -> {
          Entidad entidad = entry.getKey();
          List<Incidente> incidentesDeEntidad = entry.getValue();

          // Calcular la suma de tiempos de resolución de incidentes
          double sumaTiemposResolucion = incidentesDeEntidad.stream()
              .mapToDouble(incidente -> incidente.calcularDuracionCierreEnMinutos())
              .sum();

          // Contar la cantidad de incidentes no resueltos
          long cantidadNoResueltos = incidentesDeEntidad.stream()
              .filter(incidente -> !incidente.getEstado())
              .count();

          // Calcular el impacto según la ecuación dada
          double impacto = sumaTiemposResolucion + cantidadNoResueltos * coeficienteNoResueltos;

          // Multiplicar por la cantidad de miembros afectados
          //impacto *= entidad.getCantidadMiembros();


          // Crear un objeto Item con la entidad y su impacto
          return new Item(entidad.getNombre(), impacto);
        })
        .sorted(Comparator.comparingDouble(Item::getValor).reversed()) // Ordenar por impacto descendente
        .collect(Collectors.toList());

    return ranking;
  }

}
