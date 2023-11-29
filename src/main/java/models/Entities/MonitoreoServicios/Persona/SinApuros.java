package models.Entities.MonitoreoServicios.Persona;

import models.Entities.Notificador.PlanificadorDeNotificaciones;
import models.Entities.Notificador.Notificacion;

public class SinApuros implements TipoNotificacion{
    public void notificar(Notificacion notificacion){
        PlanificadorDeNotificaciones.agregarNotificacionPendiente(notificacion);
    }
}
