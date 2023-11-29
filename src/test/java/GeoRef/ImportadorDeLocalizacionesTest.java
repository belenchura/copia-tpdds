package GeoRef;

import models.Entities.MonitoreoServicios.Localizacion.Provincia;
import org.junit.jupiter.api.Test;
import models.Entities.GeoRef.ImportadorDeLocalizaciones;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImportadorDeLocalizacionesTest {

    @Test
    void seInstanciaProvinciaCorrectamente() throws IOException {
        List<Provincia> listadoDeProvincias = ImportadorDeLocalizaciones.importarLocalizaciones();
        assertTrue(listadoDeProvincias.stream().filter(p->p.getNombre()=="Buenos Aires").collect(Collectors.toList()).size()==1);
    }

}