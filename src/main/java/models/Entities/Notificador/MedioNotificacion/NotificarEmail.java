package models.Entities.Notificador.MedioNotificacion;

import models.Entities.MonitoreoServicios.Persona.Persona;

public class NotificarEmail implements MedioNotificacion{
    private AdapterEmail adapterEmail;
    public void notificar(Persona persona, String mensaje){
        adapterEmail.notificar(persona,mensaje);
    }
}
