package server.routes;


import controllers.FactoryController;
import controllers.MiembrosComunidadController;
import controllers.ServicioMiembroController;
import models.Entities.MonitoreoServicios.Comunidad.Comunidad;
import models.Entities.MonitoreoServicios.Persona.TipoRol;
import server.Server;
import controllers.ComunidadesController;

import java.util.function.Predicate;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ComunidadRouter {
    public static void init(){
        Server.app().routes(()->{
            path("comunidades",()->{
                get("/",(((ComunidadesController) FactoryController.controller("Comunidades"))::index));
                get("/{idComunidad}", (((ComunidadesController) FactoryController.controller("Comunidades"))::show));
                get("/{idComunidad}/unirse",((ComunidadesController) ( FactoryController.controller("Comunidades")))::unirse);
                get("/{idComunidad}/editarTipoMiembro",((ServicioMiembroController) (FactoryController.controller("ServicioMiembro")))::index);
                get("/miembrosComunidad/{idComunidad}", ((MiembrosComunidadController) FactoryController.controller("Miembro"))::show);
                post("/miembrosComunidad/{miembroId}", ((MiembrosComunidadController) FactoryController.controller("Miembro"))::update);
            });
        });
    }
}
