package controllers;

import io.javalin.http.Context;
import models.Entities.MonitoreoServicios.Establecimiento.Establecimiento;
import models.Entities.MonitoreoServicios.Prestacion;
import models.Entities.MonitoreoServicios.Servicio.Servicio;
import models.Repositories.RepositorioServicios;
import models.Repositories.RepositorioTemplate;
import server.routes.Router;
import server.utils.DisplayServicios;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class ServiciosController extends Controller implements ICrudViewsHandler {
    private RepositorioServicios repositorioServicios;

    public ServiciosController(RepositorioServicios repositorioServicios){
        this.repositorioServicios = repositorioServicios;
    }

    @Override
    public void index(Context context) {
        //Devuelve una vista que permite mostrar el listado de todos los recursos (acá puede haber filtros)
        Map<String, Object> model = new HashMap<>();
        //List<Servicio> servicios = this.repositorioServicios.buscarTodosPorIdEstablecimiento(parseLong(context.pathParam("id-establecimiento")));
        RepositorioTemplate<Establecimiento> repositorioEstablecimiento = new RepositorioTemplate<Establecimiento>(Establecimiento.class);
        Establecimiento establecimiento = repositorioEstablecimiento.buscar(Long.parseLong(context.pathParam("id-establecimiento")));
        List<Prestacion> prestaciones = establecimiento.getPrestaciones();
        List<DisplayServicios> displayServicios = prestaciones.stream().map(prestacion -> new DisplayServicios(prestacion.getId(),prestacion.getServicio().getNombre())).collect(Collectors.toList());
        model.put("displayServicios",displayServicios);
        model.putAll(Router.getDefaultAttributes(context));
        context.render("/apertura_incidentes_seleccionar_servicio.hbs", model);
    }

    @Override
    public void show(Context context) {
        //Devuelve una vista que permite mostrar el detalle del recurso

    }

    @Override
    public void create(Context context) {
        //Devuelve una vista que permite crear el recurso

    }

    @Override
    public void save(Context context) {
        //Guarda el recurso en la base de datos

    }

    @Override
    public void edit(Context context) {
        //Devuelve una vista que permite editar el recurso

    }

    @Override
    public void update(Context context) {
        //Actualiza el recurso

    }

    @Override
    public void delete(Context context) {
        //Elimina el recurso

    }
}
