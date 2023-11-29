package controllers;

import io.javalin.http.Context;
import models.Entities.MonitoreoServicios.Comunidad.ServicioMiembro;
import models.Entities.MonitoreoServicios.Comunidad.TipoMiembro;
import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Repositories.RepositorioTemplate;
import server.routes.Router;
import server.utils.DisplayServicioMiembro;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class ServicioMiembroController extends Controller implements ICrudViewsHandler {
    private RepositorioTemplate<ServicioMiembro> repositorioServicioMiembro;

    public ServicioMiembroController(RepositorioTemplate<ServicioMiembro> repositorioServicioMiembro){
        this.repositorioServicioMiembro = repositorioServicioMiembro;
    }

    @Override
    public void index( Context context) {
        List<ServicioMiembro> servicioMiembros = this.repositorioServicioMiembro.buscarTodos();
        Persona usuario = usuarioLogueado(context);
        List<ServicioMiembro> servicioMiembrosFiltrados = servicioMiembros.stream()
                .filter(servicioMiembro -> servicioMiembro.getMiembro().getPersona().getId() == usuario.getId())
                .collect(Collectors.toList());
        List<DisplayServicioMiembro> displayServicioMiembro = servicioMiembrosFiltrados.stream().map(sm -> new DisplayServicioMiembro(
                sm.getId(),
                sm.getServicio().getNombre(),
                sm.getTipoMiembroConvertido())).collect(Collectors.toList());
        Map<String, Object> model = new HashMap<>();
        model.put("displayServicioMiembro", displayServicioMiembro);
        model.putAll(Router.getDefaultAttributes(context));
        context.render("/editar_tipo_miembro.hbs", model);
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
        String id = context.pathParam("idServicioMiembro");
        ServicioMiembro servicioMiembro = this.repositorioServicioMiembro.buscar(parseLong(id));
        if (servicioMiembro.getTipoMiembroConvertido() == "Observador") {
            servicioMiembro.setTipoMiembro(TipoMiembro.AFECTADO);
        }
        else {
            servicioMiembro.setTipoMiembro(TipoMiembro.OBSERVADOR);
        }
        this.repositorioServicioMiembro.actualizar(servicioMiembro);
    }

    @Override
    public void delete(Context context) {

    }
}
