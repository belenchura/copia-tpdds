package models.Repositories;

import models.Entities.MonitoreoServicios.Comunidad.Miembro;
import models.Entities.MonitoreoServicios.Incidente.Incidente;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class RepositorioIncidentes extends RepositorioTemplate<Incidente> {
  public RepositorioIncidentes(Class<Incidente> entityClass) {
    super(entityClass);
  }

  public List<Incidente> buscarIncidentesCerrados() {
    return entityManager().createQuery("from Incidente i where i.estado = 0").getResultList();
  }

  public List<Incidente> buscarIncidentesCerradosSemanaActual() {
    LocalDateTime inicioSemana = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0);
    LocalDateTime finSemana = LocalDateTime.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).withHour(23).withMinute(59).withSecond(59);

    return entityManager()
        .createQuery("from Incidente i where i.estado = 0 and i.fechaYHoraCierre between :inicioSemana and :finSemana", Incidente.class)
        .setParameter("inicioSemana", inicioSemana)
        .setParameter("finSemana", finSemana)
        .getResultList();
  }

  public List<Incidente> buscarIncidentesAbiertosSemanaActual() {
    LocalDateTime inicioSemana = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0);
    LocalDateTime finSemana = LocalDateTime.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).withHour(23).withMinute(59).withSecond(59);

    return entityManager()
        .createQuery("from Incidente i where i.estado = 1 and i.fechaYHoraApertura between :inicioSemana and :finSemana ORDER BY i.fechaYHoraApertura ASC", Incidente.class)
        .setParameter("inicioSemana", inicioSemana)
        .setParameter("finSemana", finSemana)
        .getResultList();
  }

}
