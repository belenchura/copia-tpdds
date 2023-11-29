package controllers;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import models.Entities.MonitoreoServicios.Persona.Persona;

public abstract class Controller implements WithSimplePersistenceUnit {
    protected Persona usuarioLogueado(Context ctx) {
        if(ctx.sessionAttribute("user_id") == null)
            return null;
        return entityManager()
                .find(Persona.class, Long.parseLong(ctx.sessionAttribute("user_id")));
    }
}
