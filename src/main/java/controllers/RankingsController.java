package controllers;


import models.Entities.MonitoreoServicios.Comunidad.Comunidad;
import models.Entities.MonitoreoServicios.Comunidad.Miembro;
import models.Entities.MonitoreoServicios.Entidad.Entidad;
import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.MonitoreoServicios.Prestacion;
import models.Entities.Ranking.Criterios.CantidadIncidentesPorEntidad;
import models.Entities.Ranking.Criterios.ImpactoIncidentesPorComunidad;
import models.Entities.Ranking.Criterios.PromedioCierreIncidentePorEntidad;
import models.Entities.Ranking.DataRanking.Item;
import models.Repositories.RepositorioIncidentes;
import models.Repositories.RepositorioTemplate;
import server.routes.Router;
import server.utils.DisplayIncidentes;
import server.utils.DisplayItemRanking;
import server.utils.DisplayMiembros;
import server.utils.ICrudViewsHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import io.javalin.http.Context;

import static java.lang.Long.parseLong;

public class RankingsController extends Controller implements ICrudViewsHandler {
  private RepositorioIncidentes repositorioIncidentes;
  private RepositorioTemplate<Entidad> repositorioEntidades;

  public RankingsController(RepositorioIncidentes repositorioIncidentes, RepositorioTemplate<Entidad> repositorioEntidades) {
    this.repositorioIncidentes = repositorioIncidentes;
    this.repositorioEntidades = repositorioEntidades;
  }

  //RANKING 2
  public void ranking2(Context context) {
    Map<String, Object> model = new HashMap<>();
    List<Incidente> incidentesAbiertosSemanaActual = this.repositorioIncidentes.buscarIncidentesAbiertosSemanaActual();
    List<Entidad> entidadesTotales = this.repositorioEntidades.buscarTodos(); // Definir si filtro por entidad
    List<Item> itemsOrdenados = new CantidadIncidentesPorEntidad(entidadesTotales).generarRanking(incidentesAbiertosSemanaActual);

    List<DisplayItemRanking> displayItems = itemsOrdenados.stream().map(i -> new DisplayItemRanking(
        i.getNombre(),
        i.getValor()
    )).collect(Collectors.toList());

    model.put("criterio","Cantidad Incidentes Abiertos Semana");
    model.put("displayItemsRanking",itemsOrdenados);

    model.putAll(Router.getDefaultAttributes(context));

    context.render("/rankings.hbs", model);
  }

  //RANKING 1
  public void ranking1(Context context) {
    Map<String, Object> model = new HashMap<>();
    List<Incidente> incidentesCerradosSemanales = this.repositorioIncidentes.buscarIncidentesCerradosSemanaActual();
    List<Entidad> entidadesTotales = this.repositorioEntidades.buscarTodos(); // Definir si filtro por entidad
    List<Item> itemsOrdenados = new PromedioCierreIncidentePorEntidad(entidadesTotales).generarRanking(incidentesCerradosSemanales);

    List<DisplayItemRanking> displayItems = itemsOrdenados.stream().map(i -> new DisplayItemRanking(
        i.getNombre(),
        i.getValor()
    )).collect(Collectors.toList());


    model.put("criterio","Promedio Cierre Incidente");
    model.put("displayItemsRanking",itemsOrdenados);

    model.putAll(Router.getDefaultAttributes(context));

    context.render("/rankings.hbs", model);
  }

  public void ranking3(Context context) {
    Map<String, Object> model = new HashMap<>();
    List<Incidente> incidentesAbiertosSemanaActual = this.repositorioIncidentes.buscarIncidentesAbiertosSemanaActual();
    List<Entidad> entidadesTotales = this.repositorioEntidades.buscarTodos(); // Definir si filtro por entidad
    List<Item> itemsOrdenados = new ImpactoIncidentesPorComunidad(entidadesTotales).generarRanking(incidentesAbiertosSemanaActual);

    List<DisplayItemRanking> displayItems = itemsOrdenados.stream().map(i -> new DisplayItemRanking(
        i.getNombre(),
        i.getValor()
    )).collect(Collectors.toList());


    model.put("criterio","Promedio Cierre Incidente");
    model.put("displayItemsRanking",itemsOrdenados);

    model.putAll(Router.getDefaultAttributes(context));

    context.render("/rankings.hbs", model);
  }

  @Override
  public void index(Context context) {
  }
  @Override
  public void show(Context context) {
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
  }

  public void actualizar(Context context){
  }

  @Override
  public void delete(Context context) {
  }

  private void asignarParametros(Incidente incidente, Context context) {
  }

  private void actualizarAtributos(Incidente incidente, Context context) {
  }
}