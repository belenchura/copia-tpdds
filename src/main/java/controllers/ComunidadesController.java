package controllers;

import io.javalin.http.Context;
import models.Entities.MonitoreoServicios.Comunidad.Comunidad;
import models.Entities.MonitoreoServicios.Comunidad.Miembro;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Entities.MonitoreoServicios.Persona.TipoRol;
import models.Repositories.RepositorioTemplate;
import server.routes.Router;
import server.utils.DatosComunidad;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class ComunidadesController extends Controller implements ICrudViewsHandler {
    private RepositorioTemplate<Comunidad> repositorioComunidades;
    public ComunidadesController(RepositorioTemplate<Comunidad> repositorioComunidades){
        this.repositorioComunidades= repositorioComunidades;
    }
    @Override
    public void index( Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Comunidad> comunidadeslist= this.repositorioComunidades.buscarTodos();

        List<DatosComunidad> comunidades= comunidadeslist.stream()
                .map(comunidad -> {
                    System.out.println(comunidad.getNombre());
                    System.out.println(comunidad.getMiembros().size());
                 return  new DatosComunidad(comunidad,((Map<String, TipoRol>) context.sessionAttribute("comunidad_rol")).containsKey(comunidad.getId().toString()));
                }).collect(Collectors.toList());
        model.put("comunidades",comunidades);

        model.putAll(Router.getDefaultAttributes(context));
        context.render("/comunidades.hbs", model);
    }
    public void unirse(Context context){
        System.out.println("paso1");
        Persona usuario= usuarioLogueado(context);
        if(context.pathParam("idComunidad")!=null){
            System.out.println(context.pathParam("idComunidad"));
            Optional<Comunidad> comunidad= Optional.of( this.repositorioComunidades.buscar(Long.parseLong(context.pathParam("idComunidad"))));

            comunidad.ifPresent(comu ->{
                if(!( (Map<String,TipoRol>) context.sessionAttribute("comunidad_rol")).containsKey(comu.getId().toString())){
                    usuario.getRolComunidad().put(comu.getId().toString(), TipoRol.MEMBER);
                    Miembro miembro= new Miembro(usuario);
                    miembro.setComunidad(comu);
                    comu.setMiembros(miembro);
                    this.repositorioComunidades.actualizar(comu);
                    //(new RepositorioTemplate<>(Persona.class)).actualizar(usuario);
                    context.sessionAttribute("comunidad_rol",usuario.getRolComunidad());
                }

            });
        }
        context.redirect("/comunidades");
    }

    @Override
    public void show(Context context) {
        Comunidad comunidad= this.repositorioComunidades.buscar(Long.parseLong(context.pathParam("idComunidad")));

        Map<String, Object> model = new HashMap<>();
        model.putAll(Router.getDefaultAttributes(context));
        model.put("comunidad", comunidad);
        context.render("/comunidad.hbs", model);
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

    @Override
    public void delete(Context context) {

    }
}
