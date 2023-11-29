package server.routes;

import controllers.FactoryController;
import controllers.ServicioMiembroController;
import server.Server;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ServicioMiembroRouter {
    public static void init(){
        Server.app().routes(()->{
            post("servicioMiembro/{idServicioMiembro}",((ServicioMiembroController) (FactoryController.controller("ServicioMiembro")))::update);
        });
    }
}