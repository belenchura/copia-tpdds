package controllers;

import io.javalin.http.Context;
import models.Entities.MonitoreoServicios.Establecimiento.Establecimiento;
import models.Repositories.RepositorioEstablecimientos;
import server.routes.Router;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;

public class EstablecimientosController extends Controller implements ICrudViewsHandler {
    private RepositorioEstablecimientos repositorioEstablecimientos;

    public EstablecimientosController(RepositorioEstablecimientos repositorioEstablecimientos){
        this.repositorioEstablecimientos = repositorioEstablecimientos;
    }

    @Override
    public void index(Context context) {
        //Devuelve una vista que permite mostrar el listado de todos los recursos (acá puede haber filtros)
        Map<String, Object> model = new HashMap<>();
        List<Establecimiento> establecimientos = this.repositorioEstablecimientos.buscarTodosPorIdEntidad(parseLong(context.pathParam("id-entidad")));
        System.out.println("TAMAÑO"+establecimientos.size());
        model.put("establecimientos",establecimientos);
        model.putAll(Router.getDefaultAttributes(context));
        context.render("/apertura_incidentes_seleccionar_establecimiento.hbs", model);
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
