package FusionDeComunidades;

import models.Entities.FusionDeComunidades.Servicio1;
import models.Entities.FusionDeComunidades.entities.ComunidadServicio1;
import models.Entities.FusionDeComunidades.entities.SolicitudFusion;
import models.Entities.FusionDeComunidades.entities.solicitud.EstablecimientoSolicitud;
import models.Entities.FusionDeComunidades.entities.solicitud.MiembroSolicitud;
import models.Entities.FusionDeComunidades.entities.solicitud.ServicioSolicitud;

import java.io.IOException;
import java.util.ArrayList;

public class EjemploDeUsoFusion {
    public static void main(String[] args) throws IOException {
        SolicitudFusion solicitudFusion = new SolicitudFusion();

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

        solicitudFusion.comunidad_principal = comunidadPrincipal;

        //ComunidadFusionable
        ComunidadServicio1 comunidadFusionable = new ComunidadServicio1();
        comunidadFusionable.nombre = "Fusionable";

        comunidadFusionable.miembros = new ArrayList<>();
        MiembroSolicitud miembro2 = new MiembroSolicitud();
        miembro2.nombre = "Miembro2";
        comunidadFusionable.setMiembro(miembro1);
        comunidadFusionable.setMiembro(miembro2);

        comunidadFusionable.gradoDeConfianza = 100;

        comunidadFusionable.establecimientos = new ArrayList<>();
        comunidadFusionable.setEstablecimiento(establecimiento1);

        comunidadFusionable.servicios = new ArrayList<>();
        comunidadFusionable.setServicio(servicio1);

        solicitudFusion.comunidad_fusionable = comunidadFusionable;



        Servicio1 instanciaServicio1 = Servicio1.instancia();
        ComunidadServicio1 comunidadRespuesta = instanciaServicio1.fuse_communities(solicitudFusion);
        System.out.println(comunidadRespuesta.nombre);
        System.out.println(comunidadRespuesta.gradoDeConfianza);
    }
}
