package CargaDeDatos;

import models.Entities.CargaDeDatos.AdapterOpenCSV;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdapterOpenCSVTest {
    static AdapterOpenCSV adapterOpenCSV = new AdapterOpenCSV();
    static String path = "C:\\Users\\matia\\OneDrive\\Escritorio\\organismoDeControl.csv";

    @Test
    void seLeeElArchivoCorrectamente (){
        assertEquals("BurgerKing",adapterOpenCSV.leerLineas(path).get(1)[0]);
    }
    @Test
    void seLeeElArchivoCorrectamente2 (){
        assertEquals("Roberto",adapterOpenCSV.leerLineas(path).get(1)[1]);
    }
}