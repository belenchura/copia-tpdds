package server;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.config.SizeUnit;
import io.javalin.http.HttpStatus;
import io.javalin.rendering.JavalinRenderer;
import models.Entities.MonitoreoServicios.Comunidad.ServicioMiembro;
import server.handlers.AppHandlers;
import server.helpers.HelperSource;
import server.middleware.AuthMiddleware;
import server.routes.*;

import java.util.function.Consumer;
public class Server {
    private static Javalin app = null;

    public static Javalin app() {
        if (app == null)
            throw new RuntimeException("App no inicializada");
        return app;
    }

    public static void init() {
        if (app == null) {
            Integer port = Integer.parseInt(System.getProperty("port", "8080"));
            app = Javalin.create(config()).start(port);
            initTemplateEngine();
            AppHandlers.applyHandlers(app);
            Router.init();
            ComunidadRouter.init();
            IncidentesRouter.init();
            ServicioMiembroRouter.init();
        }
    }

    private static Consumer<JavalinConfig> config() {
        return config -> {
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/static";
                staticFiles.directory = "/public";
            });

            AuthMiddleware.apply(config);
            config.jetty.multipartConfig.cacheDirectory("c:/temp"); //where to write files that exceed the in memory limit
            config.jetty.multipartConfig.maxFileSize(10, SizeUnit.MB); //the maximum individual file size allowed
            config.jetty.multipartConfig.maxInMemoryFileSize(10, SizeUnit.MB); //the maximum file size to handle in memory
            config.jetty.multipartConfig.maxTotalRequestSize(1, SizeUnit.GB); //the maximum size of the entire multipart request
        };
    }


    private static void initTemplateEngine() {
        JavalinRenderer.register(
                (path, model, context) -> { // Función que renderiza el template
                    TemplateLoader loader= new ClassPathTemplateLoader("/templates",".hbs");
                    Handlebars handlebars = new Handlebars(loader);

                    Template template = null;

                    try {

                        //handlebars.registerHelpers(new File("templates/helpers/get-route.js"));
                        handlebars.registerHelpers(new HelperSource());

                        template = handlebars.compile(
                                 path.replace(".hbs", ""));
                        return template.apply(model);
                    } catch ( Exception e) {
                        e.printStackTrace();
                        context.status(HttpStatus.NOT_FOUND);
                        return "No se encuentra la página indicada...";
                    }
                }, ".hbs" // Extensión del archivo de template
        );
    }
}