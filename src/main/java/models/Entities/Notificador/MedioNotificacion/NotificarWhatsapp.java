package models.Entities.Notificador.MedioNotificacion;

import models.Entities.MonitoreoServicios.Persona.Persona;

public class NotificarWhatsapp implements MedioNotificacion {
    private AdapterWhatsapp adapterWhatsapp;
    public void notificar(Persona persona, String mensaje){
        adapterWhatsapp.notificar(persona,mensaje);
    }
}