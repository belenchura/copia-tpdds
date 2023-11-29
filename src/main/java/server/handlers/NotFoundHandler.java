package server.handlers;

import io.javalin.Javalin;
import org.eclipse.jetty.http.pathmap.MatchedPath;
import server.routes.Router;

import java.util.HashMap;
import java.util.Map;

public class NotFoundHandler implements IHandler {

    @Override
    public void setHandle(Javalin app) {
        app.error(404, ctx -> {
            Map<String, Object> model = new HashMap<>(Router.getDefaultAttributes(ctx));
            ctx.status(404);
            ctx.render("404.hbs",model);
        });
    }
}
