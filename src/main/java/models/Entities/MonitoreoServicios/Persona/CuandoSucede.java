package models.Entities.MonitoreoServicios.Persona;

import models.Entities.Notificador.Notificacion;

public class CuandoSucede implements TipoNotificacion{
    public void notificar(Notificacion notificacion){
        Persona receptor = notificacion.getReceptor();
        receptor.getMedioNotificacion().notificar(receptor,notificacion.getMensajeAEnviar());
    }
}
