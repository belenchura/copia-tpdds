package controllers;

import models.Entities.MonitoreoServicios.Comunidad.Comunidad;
import models.Entities.MonitoreoServicios.Comunidad.Miembro;
import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.MonitoreoServicios.Prestacion;
import models.Repositories.RepositorioEstablecimientos;
import models.Repositories.RepositorioMiembros;
import models.Repositories.RepositorioTemplate;
import server.routes.Router;
import server.utils.DisplayIncidentes;
import server.utils.DisplayMiembros;
import server.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class MiembrosComunidadController  extends Controller implements ICrudViewsHandler {
  private RepositorioMiembros repositorioMiembros;
  public MiembrosComunidadController(RepositorioMiembros repositorioMiembros){
    this.repositorioMiembros = repositorioMiembros;
  }
  @Override
  // Mostrar todos miembros por comunidad
  // Parametro: id (comunidad)
  public void index( Context context) {
    Map<String, Object> model = new HashMap<>();

    List<Miembro> miembrosPorComunidad = this.repositorioMiembros.buscarPorComunidad(1L);

    List<DisplayMiembros> displayMiembros = miembrosPorComunidad.stream().map(i -> new DisplayMiembros(
        i.getId(),
        i.getPersona().getNombre(),
        i.getPersona().getApellido()
    )).collect(Collectors.toList());

    model.put("displayMiembros",displayMiembros);
    model.putAll(Router.getDefaultAttributes(context));

    context.render("/dar_de_baja_miembro.hbs", model);
  }

  @Override
  public void show(Context context) {

    Map<String, Object> model = new HashMap<>();

    List<Miembro> miembrosPorComunidad = this.repositorioMiembros.buscarPorComunidad(parseLong(context.pathParam("idComunidad")));

    List<Miembro> miembrosActivos = miembrosPorComunidad.stream()
            .filter(miembro -> miembro.getEstado())
            .collect(Collectors.toList());

    List<DisplayMiembros> displayMiembros = miembrosActivos.stream().map(i -> new DisplayMiembros(
        i.getId(),
        i.getPersona().getNombre(),
        i.getPersona().getApellido()
    )).collect(Collectors.toList());

    model.put("displayMiembros",displayMiembros);
    model.putAll(Router.getDefaultAttributes(context));

    context.render("/dar_de_baja_miembro.hbs", model);

  }
  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {

  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {
    String id = context.pathParam("miembroId");
    Miembro miembro = (Miembro) this.repositorioMiembros.buscar(parseLong(context.pathParam("miembroId")));
    miembro.setEstado(false);
    this.repositorioMiembros.actualizar(miembro);
    context.redirect("/");
  }
  @Override
  public void delete( Context context) {
    //Elimina el recurso
  }
}
