package models.Repositories;

import models.Entities.MonitoreoServicios.Establecimiento.Establecimiento;
import models.Entities.MonitoreoServicios.Servicio.Servicio;

import java.util.List;

public class RepositorioServicios extends RepositorioTemplate<Servicio> {
    public RepositorioServicios(Class<Servicio> entityClass) {
        super(entityClass);
    }
    public List<Servicio> buscarTodosPorIdEstablecimiento(Long idEstablecimiento) {
        return entityManager().createQuery("from Servicio where id = " + idEstablecimiento).getResultList();
    }
}
