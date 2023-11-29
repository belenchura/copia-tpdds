package models.Entities.Notificador.MedioNotificacion;

import models.Entities.MonitoreoServicios.Persona.Persona;

public interface AdapterWhatsapp {
    public void notificar(Persona persona, String mensaje);
}