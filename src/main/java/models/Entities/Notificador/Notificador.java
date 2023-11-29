package models.Entities.Notificador;


public class Notificador {
    /*
    public static void notificarAperturaOCierreIncidente(Incidente incidente){
         generarListaInteresados(incidente).forEach(persona -> {
            Notificacion notificacion = Notificacion.builder()
                             .receptor(persona)
                            .incidente(incidente)
                            .esDeApertura(incidente.getEstado())
                            .mensajeAEnviar(GeneradorDeMensajes.generarMensajeAperturaOCierre(incidente))
                            .build();
            persona.getTipoNotificacion().notificar(notificacion);
         });
    }

    public void notificarRevisionIncidente(Persona persona){
        Incidente incidenteMasCercano = Distancia.obtenerIncidenteMasCercano(persona);
        if(incidenteMasCercano!=null) {
            String mensaje = GeneradorDeMensajes.generarMensajeRevision(incidenteMasCercano);
            persona.getMedioNotificacion().notificar(persona,mensaje);
        }
    }

    public static List<Persona> generarListaInteresados(Incidente incidente){
        List<Persona> listaConRepetidos= RepositorioPersonas.getListaPersonas().stream().filter(persona ->  persona.getInteres().getServicios().contains(incidente.getPrestacion().getServicio())).collect(Collectors.toList());
        listaConRepetidos.addAll(RepositorioComunidades.getListaComunidades().stream().filter(c->c.getMiembros().contains(incidente.getMiembroApertura())).flatMap(c->c.getMiembros().stream()).map(m->m.getPersona()).collect(Collectors.toList()));

        // Eliminar elementos duplicados manteniendo el tipo ArrayList
        List<Persona> listaSinDuplicados = new ArrayList<>();
        Set<Persona> conjunto = new HashSet<>();

        for (Persona elemento : listaConRepetidos) {
            if (conjunto.add(elemento)) {
                listaSinDuplicados.add(elemento);
            }
        }

        return listaSinDuplicados;
    }*/
}