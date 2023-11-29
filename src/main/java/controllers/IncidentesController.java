package controllers;

import io.javalin.http.Context;
import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.MonitoreoServicios.Prestacion;
import models.Repositories.RepositorioTemplate;
import server.routes.Router;
import server.utils.DisplayIncidentes;
import server.utils.DisplayServicios;
import server.utils.ICrudViewsHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class IncidentesController extends Controller implements ICrudViewsHandler {
    private RepositorioTemplate<Incidente> repositorioIncidentes;

    public IncidentesController(RepositorioTemplate<Incidente> repositorioIncidentes) {
        this.repositorioIncidentes = repositorioIncidentes;
    }

    @Override
    public void index(Context context) {
        //Devuelve una vista que permite mostrar el listado de todos los recursos (acá puede haber filtros)
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repositorioIncidentes.buscarTodos();
        List<Incidente> incidentesAbiertos = incidentes.stream()
                .filter(incidente -> incidente.getEstado())
                .collect(Collectors.toList());
        List<DisplayIncidentes> displayIncidentes = incidentesAbiertos.stream().map(i -> new DisplayIncidentes(
                i.getId(),
                i.getEstado(),
                i.getPrestacion().getEstablecimiento().getEntidad().getNombre(),
                i.getPrestacion().getEstablecimiento().getNombre(),
                i.getPrestacion().getServicio().getNombre())).collect(Collectors.toList());
        model.put("displayIncidentes", displayIncidentes);
        model.putAll(Router.getDefaultAttributes(context));
        context.render("/cierre_incidentes.hbs", model);
    }

    public void index_listado_de_incidentes(Context context) {
        //Devuelve una vista que permite mostrar el listado de todos los recursos (acá puede haber filtros)
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repositorioIncidentes.buscarTodos();
        List<Incidente> incidentesAbiertos = incidentes.stream()
                .collect(Collectors.toList());
        List<DisplayIncidentes> displayIncidentes = incidentesAbiertos.stream().map(i -> new DisplayIncidentes(
                i.getId(),
                i.getEstado(),
                i.getPrestacion().getEstablecimiento().getEntidad().getNombre(),
                i.getPrestacion().getEstablecimiento().getNombre(),
                i.getPrestacion().getServicio().getNombre())).collect(Collectors.toList());
        model.put("displayIncidentes", displayIncidentes);
        model.putAll(Router.getDefaultAttributes(context));
        context.render("/listado_de_incidentes.hbs", model);
    }

    @Override
    public void show(Context context) {
        //Devuelve una vista que permite mostrar el detalle del recurso
        Incidente incidente = (Incidente) this.repositorioIncidentes.buscar(parseLong(context.pathParam("id-incidente")));
        Map<String, Object> model = new HashMap<>();
        Prestacion prestacion = incidente.getPrestacion();
        model.put("incidente", incidente);
        model.put("establecimiento", prestacion.getEstablecimiento());
        model.put("servicio", prestacion.getServicio());
        model.putAll(Router.getDefaultAttributes(context));
        context.render("revision_de_incidentes.hbs", model);
    }

    @Override
    public void create(Context context) {
        //Devuelve una vista que permite crear el recurso
        Map<String, Object> model = new HashMap<>();
        model.put("incidentes", null);
        context.render("/cierre_incidentes.hbs", model);
    }

    @Override
    public void save(Context context) {
        //Guarda el recurso en la base de datos
        Incidente incidente = new Incidente();
        this.asignarParametros(incidente, context);
        this.repositorioIncidentes.guardar(incidente);
        context.redirect("/");
    }

    @Override
    public void edit(Context context) {
        //Devuelve una vista que permite editar el recurso
        String id = context.pathParam("idIncidente");
        Incidente incidente = this.repositorioIncidentes.buscar(parseLong(id));
        Map<String, Object> model = new HashMap<>();
        model.put("incidentes", incidente);
        context.render("/cierre_incidentes.hbs", model);
    }

    @Override
    public void update(Context context) {
        //Actualiza el recurso
        String id = context.pathParam("id-incidente");
        Incidente incidente = this.repositorioIncidentes.buscar(parseLong(id));
        if (context.formParam("revision").equals("No")) {
            actualizarAtributos(incidente, context);
            this.repositorioIncidentes.actualizar(incidente);
        }
        context.redirect("/");
    }

    public void actualizar(Context context){
        String id = context.pathParam("id-incidente");
        Incidente incidente = this.repositorioIncidentes.buscar(parseLong(id));
        actualizarAtributos(incidente, context);
        this.repositorioIncidentes.actualizar(incidente);
        context.redirect("/incidentes/cierre");
    }

    @Override
    public void delete(Context context) {
        //Elimina el recurso
        Incidente incidente = (Incidente) this.repositorioIncidentes.buscar(parseLong(context.pathParam("id")));
        this.repositorioIncidentes.eliminar(incidente);
        context.redirect("/incidentes");
    }

    private void asignarParametros(Incidente incidente, Context context) {
        incidente.setEstado(true);
        incidente.setFechaYHoraApertura(LocalDateTime.now());
        incidente.setObservaciones(context.formParam("descripcion"));
        incidente.setPersonaApertura(usuarioLogueado(context));
        RepositorioTemplate<Prestacion> repositorioPrestacion = new RepositorioTemplate<Prestacion>(Prestacion.class);
        Prestacion prestacion = repositorioPrestacion.buscar(Long.parseLong(context.queryParam("prestacion")));
        incidente.setPrestacion(prestacion);
        /*if(!Objects.equals(context.formParam("nombre"), "")) {
            incidente.setObservaciones("seGuardoBien");
        }*/
    }

    private void actualizarAtributos(Incidente incidente, Context context) {
        incidente.setEstado(false);
        incidente.setFechaYHoraCierre(LocalDateTime.now());
    }
}