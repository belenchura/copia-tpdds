package server.handlers;

import com.sun.tools.jconsole.JConsoleContext;
import io.javalin.Javalin;
import server.exceptions.AccessDeniedException;
import server.routes.Router;

import java.util.HashMap;
import java.util.Map;

public class AccessDeniedHandler implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(AccessDeniedException.class,(e, context)->{
            if(context.sessionAttribute("user_id")==null){
                context.redirect("/login?redirect="+context.path());
            }else {
                Map<String,Object> model= new HashMap<>();
                model.putAll(Router.getDefaultAttributes(context));
                context.status(401).render("401.hbs",model);
            }
        });
    }
}
