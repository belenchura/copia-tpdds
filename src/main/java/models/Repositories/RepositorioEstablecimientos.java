package models.Repositories;

import models.Entities.MonitoreoServicios.Establecimiento.Establecimiento;

import java.util.List;

public class RepositorioEstablecimientos extends RepositorioTemplate<Establecimiento> {
    public RepositorioEstablecimientos(Class<Establecimiento> entityClass) {
        super(entityClass);
    }

    public List<Establecimiento> buscarTodosPorIdEntidad(Long idEntidad) {
        return entityManager().createQuery("select e from Establecimiento e where e.entidad.id = " + idEntidad).getResultList();
    }
}
