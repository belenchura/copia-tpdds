package FusionDeComunidades;

import models.Entities.FusionDeComunidades.Servicio1;
import models.Entities.FusionDeComunidades.entities.ComunidadServicio1;
import models.Entities.FusionDeComunidades.entities.solicitud.EstablecimientoSolicitud;
import models.Entities.FusionDeComunidades.entities.solicitud.MiembroSolicitud;
import models.Entities.FusionDeComunidades.entities.solicitud.ServicioSolicitud;
import models.Entities.FusionDeComunidades.entities.solicitud.SolicitudPropuesta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EjemploDeUsoPropuesta {
    public static void main(String[] args) throws IOException {
        SolicitudPropuesta solicitudPropuesta = new SolicitudPropuesta();
        solicitudPropuesta.lista_comunidades = new ArrayList<>();

        //ComunidadPrincipal
        ComunidadServicio1 comunidadPrincipal = new ComunidadServicio1();
        comunidadPrincipal.nombre = "Principal";

        comunidadPrincipal.miembros = new ArrayList<>();
        MiembroSolicitud miembro1 = new MiembroSolicitud();
        miembro1.nombre = "Miembro1";
        comunidadPrincipal.setMiembro(miembro1);

        comunidadPrincipal.gradoDeConfianza = 100;

        comunidadPrincipal.establecimientos = new ArrayList<>();
        EstablecimientoSolicitud establecimiento1 = new EstablecimientoSolicitud();
        establecimiento1.nombre = "Establecimiento1";
        comunidadPrincipal.setEstablecimiento(establecimiento1);

        comunidadPrincipal.servicios = new ArrayList<>();
        ServicioSolicitud servicio1 = new ServicioSolicitud();
        servicio1.nombre = "Servicio1";
        comunidadPrincipal.setServicio(servicio1);

        solicitudPropuesta.comunidad = comunidadPrincipal;

        //Comunidad1
        ComunidadServicio1 comunidad1 = new ComunidadServicio1();
        comunidad1.nombre = "comunidad1";

        comunidad1.miembros = new ArrayList<>();
        comunidad1.setMiembro(miembro1);

        comunidad1.gradoDeConfianza = 100;

        comunidad1.establecimientos = new ArrayList<>();
        comunidad1.setEstablecimiento(establecimiento1);

        comunidad1.servicios = new ArrayList<>();
        comunidad1.setServicio(servicio1);

        solicitudPropuesta.setComunidad(comunidad1);


        //Comunidad2
        ComunidadServicio1 comunidad2 = new ComunidadServicio1();
        comunidad2.nombre = "comunidad2";

        comunidad2.miembros = new ArrayList<>();
        comunidad2.setMiembro(miembro1);

        comunidad2.gradoDeConfianza = 100;

        comunidad2.establecimientos = new ArrayList<>();
        comunidad2.setEstablecimiento(establecimiento1);

        comunidad2.servicios = new ArrayList<>();
        comunidad2.setServicio(servicio1);
        solicitudPropuesta.setComunidad(comunidad2);

        Servicio1 instanciaServicio1 = Servicio1.instancia();
        List<ComunidadServicio1> listadoDeComunidades = instanciaServicio1.propose_fusion(solicitudPropuesta);
        for(ComunidadServicio1 comunidad : listadoDeComunidades){
            System.out.println(comunidad.nombre);
            System.out.println(comunidad.gradoDeConfianza);
            System.out.println("\n");
        }
    }
}
