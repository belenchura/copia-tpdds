package models.Entities.Ranking;

import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.Ranking.DataRanking.Item;
import models.Repositories.RepositorioTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Ranking {
  private RepositorioTemplate<Incidente> repositorioIncidentes;
  private final Locale locale = Locale.FRANCE; // Locale.FRANCE comienza la semana los LUNES
  final DayOfWeek firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
  final DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);

  public Ranking(RepositorioTemplate<Incidente> repoIncidentes) {
    repositorioIncidentes = repoIncidentes;
  }


  private List<Incidente> getIncidentesSemanales() {
    LocalDate inicioSemana = LocalDate.now().with(firstDayOfWeek);
    LocalDate finSemana = LocalDate.now().with(lastDayOfWeek);

    return repositorioIncidentes.buscarTodos().stream()
        .filter(incidente -> {
          LocalDateTime fechaApertura = incidente.getFechaYHoraApertura();
          return fechaApertura.toLocalDate().isEqual(inicioSemana) ||
              (fechaApertura.toLocalDate().isAfter(inicioSemana) &&
                  fechaApertura.toLocalDate().isBefore(finSemana)) ||
              fechaApertura.toLocalDate().isEqual(finSemana);
        })
        .collect(Collectors.toList());
  }

  public List<Item> generarRanking(TipoRanking tipoRanking){
    return tipoRanking.generarRanking(getIncidentesSemanales());
  }

}
