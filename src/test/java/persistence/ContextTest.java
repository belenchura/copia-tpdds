package persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import models.Entities.MonitoreoServicios.Interes;
import models.Entities.MonitoreoServicios.Persona.Email;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Entities.MonitoreoServicios.Persona.Telefono;
import models.Entities.MonitoreoServicios.Servicio.Servicio;
import models.Repositories.RepositorioTemplate;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.WeakHashMap;

public class ContextTest implements SimplePersistenceTest {

    @Test
    void contextUp() {
        assertNotNull(entityManager());
    }

    @Test
    void contextUpWithTransaction() throws Exception {
        withTransaction(() -> {
        });
    }
    @Test
    void servicioTest(){
        Servicio servicio= new  Servicio("name","desc",new ArrayList<>());
        RepositorioTemplate<Servicio> repoServicio= new RepositorioTemplate<Servicio>(Servicio.class);
        repoServicio.guardar(servicio);
    }
    @Test
    void Admin(){
        Persona persona= new Persona("valen","manfredi",new Email("valen@utn.com"),new Telefono("1234"),new Interes());
        persona.setPassword(BCrypt.hashpw("1234",persona.getSalt()));
        RepositorioTemplate<Persona> repoPersona= new RepositorioTemplate<Persona>(Persona.class);
        repoPersona.guardar(persona);
    }

}

