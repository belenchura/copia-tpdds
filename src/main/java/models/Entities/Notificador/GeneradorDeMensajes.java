package models.Entities.Notificador;

import models.Entities.MonitoreoServicios.Incidente.Incidente;

public class GeneradorDeMensajes {
    public static String generarMensajeAperturaOCierre(Incidente incidente){
        if (incidente.getEstado()) {
            return generarMensajeApertura(incidente);
        }
        else {
            return generarMensajeCierre(incidente);
        }
    }
    private static String generarMensajeApertura(Incidente incidente){
        return "Apertura de Incidente: "+
                infoIncidente(incidente) +
                "\nFecha: " + incidente.getFechaYHoraApertura().getDayOfMonth() +"/"+ incidente.getFechaYHoraApertura().getMonth() +
                "\nHora: " + incidente.getFechaYHoraApertura().getHour() +":" +incidente.getFechaYHoraApertura().getMinute() +
                "\nInformado por: " + incidente.getPersonaApertura();
    }
    private static String generarMensajeCierre(Incidente incidente){
    return "Cierre de Incidente: " +
            infoIncidente(incidente) +
            "\nFecha: " + incidente.getFechaYHoraCierre().getDayOfMonth() +"/"+ incidente.getFechaYHoraCierre().getMonth() +
            "\nHora: " + incidente.getFechaYHoraCierre().getHour() +":" +incidente.getFechaYHoraCierre().getMinute() +
            "\nInformado por: " + incidente.getPersonaCierre();
    }
    public static String generarMensajeRevision(Incidente incidente){
        return "Sugerencia de Revision de Incidente: " +
                infoIncidente(incidente);
    }
    private static String infoIncidente(Incidente incidente){
        return "\nEstablecimiento: " + incidente.getPrestacion().getEstablecimiento().getNombre() +
                "\nServicio: " + incidente.getPrestacion().getServicio().getNombre() +
                "\nObservaciones: "+incidente.getObservaciones();
    }
}