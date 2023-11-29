package CargaDeDatos;

import models.Entities.CargaDeDatos.InstanciarEntidadPrestadora;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Repositories.RepositorioTemplate;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstanciarEntidadPrestadoraTest {
    static InstanciarEntidadPrestadora instancia = new InstanciarEntidadPrestadora(new RepositorioTemplate<>(Persona.class));
    @Test
    void seInstanciaPersonal(){
        List<String[]> arregloDeArreglos = new LinkedList<>();

        String[] arreglo1 = {"Movistar"};
        String[] arreglo2 = {"Personal"};
        String[] arreglo3 = {"Claro"};
        arregloDeArreglos.add(arreglo1);
        arregloDeArreglos.add(arreglo2);
        arregloDeArreglos.add(arreglo3);

        assertEquals("Personal",instancia.instanciar(arregloDeArreglos).get(1).getNombre());
    }

}
