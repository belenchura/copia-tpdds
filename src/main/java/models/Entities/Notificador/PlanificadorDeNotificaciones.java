package models.Entities.Notificador;

import models.Entities.MonitoreoServicios.Persona.Persona;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class PlanificadorDeNotificaciones {
    @Getter private static List<Notificacion> notificacionesPendientes;
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new CheckTask(), 0, 60 * 1000); // 60000 milisegundos = 1 minuto
    }

    static class CheckTask extends TimerTask {
        @Override
        public void run() {
            List <Notificacion> notificacionesAEnviar = new ArrayList<>();
            notificacionesPendientes.forEach(notificacion -> {
                Persona receptor = notificacion.getReceptor();
                if(esHorarioDeEnvio(notificacion)){
                    if(notificacion.getEsDeApertura() && notificacion.getIncidente().getEstado()){
                        notificacionesAEnviar.add(notificacion);
                        notificacionesPendientes.remove(notificacion);
                        notificacion.setFechayHoraEnviada(LocalDateTime.now());
                    } else if(!notificacion.getEsDeApertura()){
                        notificacionesAEnviar.add(notificacion);
                        notificacionesPendientes.remove(notificacion);
                        notificacion.setFechayHoraEnviada(LocalDateTime.now());
                    }
                }
            });
            enviarNotificaciones(notificacionesAEnviar);
        }
    }

    public static void enviarNotificaciones (List<Notificacion> notificaciones){

            Map <Persona,StringBuilder> mensajesPorPersona = new HashMap<>();

            // Agrupar las notificaciones por persona
            for (Notificacion notificacion : notificaciones) {
                mensajesPorPersona.putIfAbsent(notificacion.getReceptor(), new StringBuilder());
                mensajesPorPersona.get(notificacion.getReceptor()).append(notificacion.getMensajeAEnviar()).append("\n\n\n");
            }

            // Enviar los mensajes a cada persona
            for (Map.Entry <Persona, StringBuilder> entry : mensajesPorPersona.entrySet()) {
                Persona persona = entry.getKey();
                String mensaje = entry.getValue().toString();
                persona.getMedioNotificacion().notificar(persona,mensaje);
            }
    }

    public static Boolean esHorarioDeEnvio(Notificacion notificacion){
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime limite = ahora.minus(1, ChronoUnit.MINUTES);
        return notificacion.getFechayHoraDebeSerEnviada().isAfter(limite) && notificacion.getFechayHoraDebeSerEnviada().isBefore(ahora);
    }

    public static void agregarNotificacionPendiente(Notificacion notificacion){
        notificacionesPendientes.add(notificacion);
    }
}