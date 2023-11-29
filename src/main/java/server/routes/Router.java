package server.routes;

import com.sun.tools.jconsole.JConsoleContext;
import controllers.ComunidadesController;
import controllers.FactoryController;
import controllers.IncidentesController;
import controllers.MiembrosComunidadController;
import controllers.RankingsController;
import io.javalin.http.Context;

import models.Entities.MonitoreoServicios.Comunidad.Comunidad;
import models.Entities.MonitoreoServicios.Comunidad.Miembro;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;
import models.Entities.CargaDeDatos.AdapterOpenCSV;
import models.Entities.CargaDeDatos.CargaDeDatos;
import models.Entities.CargaDeDatos.InstanciarEntidadPrestadora;
import models.Entities.CargaDeDatos.InstanciarOrganismoDeControl;
import models.Entities.MonitoreoServicios.Interes;
import models.Entities.MonitoreoServicios.OrganismosExternos.OrganismosExternos;
import models.Entities.MonitoreoServicios.Persona.Email;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Entities.MonitoreoServicios.Persona.Telefono;
import models.Entities.MonitoreoServicios.Persona.TipoRol;
import models.Entities.MonitoreoServicios.Servicio.Servicio;
import models.Repositories.RepositorioTemplate;
import org.mindrot.jbcrypt.BCrypt;
import server.Server;
import server.middleware.AuthMiddleware;
import server.utils.LoginResponse;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.function.Predicate;

import static io.javalin.apibuilder.ApiBuilder.*;
public class Router {
    public static void init() {

        Server.app().routes(() -> {
            //home
            get("/home", ctx-> {
                Map<String,Object> model= new HashMap<>();
                model.putAll(getDefaultAttributes(ctx));
                ctx.render("/home.hbs",model);
            });

            //login
            get("/",ctx->{
                if (ctx.sessionAttribute("user_id")!=null){
                    ctx.redirect("/home");
                }
                ctx.render("login.hbs");});
            post("/",ctx->{
                if(ctx.sessionAttribute("user_id")!= null ){
                    ctx.redirect("/home");
                }
                //chequear auth
                String inputMail= ctx.formParam("email");
                String inputPass= ctx.formParam("password");

                Optional<Persona> usuario = (new RepositorioTemplate<Persona>(Persona.class)).buscarTodos().stream().filter(p->p.getEmail().getEmail().equals(inputMail)).findAny();
                System.out.println(usuario.isPresent());
                if (usuario.isPresent()) {
                    String inputHash = BCrypt.hashpw(inputPass, usuario.get().getSalt());
                    System.out.println(inputHash);
                    System.out.println(usuario.get().getPassword());
                    if (inputHash.equals(usuario.get().getPassword())) {
                        ctx.sessionAttribute("user_id", usuario.get().getId().toString());
                        ctx.sessionAttribute("tipo_rol", usuario.get().getTipoRol().toString());
                        ctx.sessionAttribute("comunidad_rol",usuario.get().getRolComunidad());
                        ctx.status(200).json(new LoginResponse(true));
                        return;
                    }

                }
                ctx.status(401).json(new LoginResponse(false));
            });
            //logout
            get("/logout",ctx -> {
                ctx.req().getSession().invalidate();
                ctx.redirect("/");
            });
            //carga de datos
            get("/cargarDatos",ctx->{
                Map<String,Object> model= new HashMap<>();
                model.putAll(getDefaultAttributes(ctx));
                ctx.render("/carga.hbs",model);
            }, TipoRol.ADMIN);
            post("/cargarDatos",ctx->{//TODO
                if(ctx.uploadedFiles().isEmpty() ||ctx.uploadedFiles().size()>1  )return;
                File file;
                UploadedFile uploadedFile= ctx.uploadedFiles().get(0);
                CargaDeDatos cargaDeDatos= new CargaDeDatos();
                cargaDeDatos.setAdapterLectorCSV(new AdapterOpenCSV());
                List<OrganismosExternos> organismosExternos = new ArrayList<>();
                if(ctx.uploadedFileMap().containsKey("archivo_organismos")){
                    cargaDeDatos.cambiarEstrategia(new InstanciarOrganismoDeControl(new RepositorioTemplate<>(Persona.class)));
                    FileUtil.streamToFile(uploadedFile.content(), "upload/" + "archivo_organismos");
                    organismosExternos= cargaDeDatos.cargarDatos("upload/archivo_organismos");
                }else if(ctx.uploadedFileMap().containsKey("archivo_entidades")){
                    cargaDeDatos.cambiarEstrategia(new InstanciarEntidadPrestadora(new RepositorioTemplate<>(Persona.class)));
                    FileUtil.streamToFile(uploadedFile.content(), "upload/" + "archivo_entidades");
                    organismosExternos= cargaDeDatos.cargarDatos("upload/archivo_entidades");
                }
                RepositorioTemplate<Object> repoOrganismos=new RepositorioTemplate<>(Object.class);
                organismosExternos.stream().forEach(organismo->repoOrganismos.guardar(organismo));

            },TipoRol.ADMIN);

            get("reporteRanking1", ((RankingsController) FactoryController.controller("Ranking"))::ranking1);
            get("reporteRanking2", ((RankingsController) FactoryController.controller("Ranking"))::ranking2);
            get("reporteRanking3", ((RankingsController) FactoryController.controller("Ranking"))::ranking3);

            get("/modificarInformacionPersonal", ctx ->{
                ((MiembrosComunidadController) FactoryController.controller("Miembro")).edit(ctx);
            });
            get("/politicaDePrivacidad", ctx ->{
                ctx.render("/politica_de_privacidad.hbs");
            });
            get("/contacto", ctx ->{
                ctx.render("/contacto.hbs");
            });
        });

    }
    public static Map<String,Object> getDefaultAttributes(Context ctx){
        Map<String,Object> mapa= new HashMap<>();
        mapa.put("path",ctx.path());
        if (ctx.sessionAttribute("user_id")!=null){
            Persona usuario= new RepositorioTemplate<Persona>(Persona.class).buscar(Long.parseLong(ctx.sessionAttribute("user_id")));
            mapa.put("user",usuario);
        }
        if (ctx.sessionAttribute("tipo_rol")!=null){
            if(TipoRol.valueOf(ctx.sessionAttribute("tipo_rol")).equals(TipoRol.ADMIN)){
                mapa.put("admin","true");
            }
        }
        if (ctx.sessionAttribute("comunidad_rol")!=null){
            mapa.putAll(ctx.sessionAttribute("comunidad_rol"));
        }

        return mapa;
    }
}

