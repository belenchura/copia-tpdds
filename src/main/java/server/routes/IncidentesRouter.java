package server.routes;

import controllers.*;
import server.Server;



import static io.javalin.apibuilder.ApiBuilder.*;

public class IncidentesRouter {
    public static void init(){
        Server.app().routes(()->{
            get("incidentes/apertura", ((EntidadesController) FactoryController.controller("Entidades"))::index);
            get("incidentes/apertura/{id-entidad}", ((EstablecimientosController) FactoryController.controller("Establecimientos"))::index);
            get("incidentes/apertura/{id-entidad}/{id-establecimiento}", ((ServiciosController) FactoryController.controller("Servicios"))::index);
            get("incidentes/apertura/{id-entidad}/{id-establecimiento}/{id-servicio}", ((DescripcionController) FactoryController.controller("Descripcion"))::mostrar);
            get("incidentes/cierre", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
            get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index_listado_de_incidentes);
            post("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::save);
            get("incidentes/{id-incidente}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
            post("incidentes/{id-incidente}", ((IncidentesController) FactoryController.controller("Incidentes"))::update);
            put("incidentes/{id-incidente}", ((IncidentesController) FactoryController.controller("Incidentes"))::actualizar);
        });
    }
}
