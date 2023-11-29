package MonitoreoServicios;

import java.util.List;

import models.Entities.MonitoreoServicios.Interes;
import models.Entities.MonitoreoServicios.Servicio.Servicio;


public class InteresTest {
    static Interes interes;
    static Servicio ascensor2,banioMujeres;
    static List<Servicio> serviciosPrueba;

    /*@BeforeAll
    static void init() {
        Provincia buenosAires = new Provincia("buenosAires", 1);
        Municipio lanus = new Municipio("lanus", 2, buenosAires);

        //LineaB
        Servicio banioHombres = new ServicioSimple("banioHombres", true);
        banioMujeres = new ServicioSimple("banioMujeres", false);
        List<Servicio> serviciosLineaB = new ArrayList<>();
        serviciosLineaB.add(banioHombres);
        serviciosLineaB.add(banioMujeres);
        Establecimiento medrano = new Establecimiento(TipoEstablecimiento.ESTACION, "medrano", serviciosLineaB, buenosAires);
        List<Establecimiento> establecimientosLineaB = new ArrayList<>();
        establecimientosLineaB.add(medrano);
        Entidad lineaB = new Entidad(TipoEntidad.SUBTERRANEO, "LineaB", establecimientosLineaB);

        //LineaH
        Servicio ascensor1 = new ServicioSimple("ascensor1", false);
        Servicio ascensor2 = new ServicioSimple("ascensor2", true);
        Servicio ascensor3 = new ServicioSimple("ascensor3", false);
        List<Servicio> serviciosLineaH = new ArrayList<>();
        serviciosLineaH.add(ascensor1);
        serviciosLineaH.add(ascensor2);
        serviciosLineaH.add(ascensor3);
        Establecimiento hospitales = new Establecimiento(TipoEstablecimiento.ESTACION, "hospitales", serviciosLineaH, lanus);
        List<Establecimiento> establecimientosLineaH = new ArrayList<>();
        establecimientosLineaH.add(hospitales);
        Entidad lineaH = new Entidad(TipoEntidad.SUBTERRANEO, "LineaH", establecimientosLineaH);

        interes = new Interes(buenosAires);
        List<Entidad> entidadesInteres = new ArrayList<>();
        entidadesInteres.add(lineaH);
        entidadesInteres.add(lineaB);
        List<Servicio> serviciosDeInteres = new ArrayList<>();
        serviciosDeInteres.add(ascensor2);
        serviciosDeInteres.add(banioHombres);
        //serviciosDeInteres.add(banioMujeres);

        interes.setEntidades(entidadesInteres);
        interes.setServicios(serviciosDeInteres);

        serviciosPrueba = new ArrayList<>();
        serviciosPrueba.add(ascensor2);
        serviciosPrueba.add(banioHombres);
        //serviciosPrueba.add(banioMujeres);
    }

    @Test
    void losServiciosSonDeInteres() {
        //assertEquals(serviciosPrueba.get(0).presentaIncidente(), true);
        assertEquals(serviciosPrueba, interes.obtenerServiciosDeInteres());
    }

    @Test
    void losServiciosNoSonDeInteres(){
        assertNotEquals(serviciosPrueba, interes.obtenerServiciosDeInteres());
        assertFalse(interes.obtenerServiciosDeInteres().contains(banioMujeres));
    }*/
}