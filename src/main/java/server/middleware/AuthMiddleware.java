package server.middleware;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import models.Entities.MonitoreoServicios.Comunidad.Comunidad;
import models.Entities.MonitoreoServicios.Persona.TipoRol;
import server.exceptions.AccessDeniedException;

import java.util.Map;

public class AuthMiddleware implements WithSimplePersistenceUnit {

    public static void apply(JavalinConfig config) {
        config.accessManager(((handler, context, routeRoles) -> {

            TipoRol userRole;

            if (context.path().startsWith("/comunidades/")) {
                if (context.path().endsWith("/unirse")){
                    userRole= getUserRoleType(context);
                } else {
                    userRole= getCommunityRoleType(context);
                }

            }else {
                userRole = getUserRoleType(context);
            }

            if (routeRoles.size() == 0 || routeRoles.contains(userRole)) {
                handler.handle(context);
            } else {
                System.out.println("ACCESO DENEGADO");
                throw new AccessDeniedException();
            }
        }));
    }

    private static TipoRol getUserRoleType(Context context) {
        return context.sessionAttribute("tipo_rol") != null?
                TipoRol.valueOf(context.sessionAttribute("tipo_rol")) : null;

    }
    private static TipoRol getCommunityRoleType(Context context){
        if (context.sessionAttribute("comunidad_rol") != null){
            if(((Map<String,?>) context.sessionAttribute("comunidad_rol")).containsKey(context.pathParam("idComunidad"))){
                return ((Map<String, TipoRol>) context.sessionAttribute("comunidad_rol")).get(context.pathParam("idComunidad"));
            }
        }
                return null;
    }
}