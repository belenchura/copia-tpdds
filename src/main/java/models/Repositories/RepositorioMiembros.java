package models.Repositories;

import models.Entities.MonitoreoServicios.Comunidad.Miembro;

import java.util.List;

public class RepositorioMiembros extends RepositorioTemplate<Miembro> {
  public RepositorioMiembros(Class<Miembro> entityClass) {
    super(entityClass);
  }

  public List<Miembro> buscarPorComunidad(Long idComunidad) {
    return entityManager().createQuery("from Miembro m where m.comunidad.id = " + idComunidad).getResultList();
  }
}