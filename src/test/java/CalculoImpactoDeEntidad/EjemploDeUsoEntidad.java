package CalculoImpactoDeEntidad;

import models.Entities.CalculoImpactoDeEntidad.Servicio3;
import models.Entities.CalculoImpactoDeEntidad.entities.respuesta.EntidadRespuesta;
import models.Entities.CalculoImpactoDeEntidad.entities.respuesta.ListadoDeEntidadesRespuesta;
import models.Entities.CalculoImpactoDeEntidad.entities.solicitud.EntidadSolicitud;
import models.Entities.CalculoImpactoDeEntidad.entities.solicitud.IncidenteSolicitud;
import models.Entities.CalculoImpactoDeEntidad.entities.solicitud.ListadoDeEntidadesSolicitud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EjemploDeUsoEntidad {
    public static void main(String[] args) throws IOException {
        ListadoDeEntidadesSolicitud listadoDeEntidadesSolicitud = new ListadoDeEntidadesSolicitud();
        listadoDeEntidadesSolicitud.entities = new ArrayList<>();

        //Entidad1
        EntidadSolicitud entidad1 = new EntidadSolicitud();
        entidad1.name = "Linea A";
        entidad1.ammountOfAffectedMembers = 5;
        entidad1.incidents = new ArrayList<>();

        IncidenteSolicitud incidente1 = new IncidenteSolicitud();
        incidente1.resTime = 99;

        List<IncidenteSolicitud> listaIncidentes1 = new ArrayList<>();
        listaIncidentes1.add(incidente1);
        entidad1.setIncidents(listaIncidentes1);
        List<EntidadSolicitud> entidadesSolicitud1 = new ArrayList<>();
        entidadesSolicitud1.add(entidad1);
        listadoDeEntidadesSolicitud.setEntities(entidadesSolicitud1);

        //Entidad2
        EntidadSolicitud entidad2 = new EntidadSolicitud();
        entidad2.name = "Linea B";
        entidad2.ammountOfAffectedMembers = 40;
        entidad2.incidents = new ArrayList<>();

        IncidenteSolicitud incidente2 = new IncidenteSolicitud();
        incidente2.resTime = -1;

        List<IncidenteSolicitud> listaIncidentes2 = new ArrayList<>();
        listaIncidentes2.add(incidente2);
        entidad2.setIncidents(listaIncidentes2);
        List<EntidadSolicitud> entidadesSolicitud2 = new ArrayList<>();
        entidadesSolicitud2.add(entidad2);
        listadoDeEntidadesSolicitud.setEntities(entidadesSolicitud2);

        //Entidad3
        EntidadSolicitud entidad3 = new EntidadSolicitud();
        entidad3.name = "Linea C";
        entidad3.ammountOfAffectedMembers = 111;
        entidad3.incidents = new ArrayList<>();

        IncidenteSolicitud incidente3 = new IncidenteSolicitud();
        incidente3.resTime = 5;

        List<IncidenteSolicitud> listaIncidentes3 = new ArrayList<>();
        listaIncidentes3.add(incidente3);
        entidad3.setIncidents(listaIncidentes3);
        List<EntidadSolicitud> entidadesSolicitud3 = new ArrayList<>();
        entidadesSolicitud3.add(entidad3);
        listadoDeEntidadesSolicitud.setEntities(entidadesSolicitud3);



        Servicio3 servicio3 = Servicio3.instancia();
        ListadoDeEntidadesRespuesta listadoDeEntidadesRespuesta = servicio3.listadoDeEntidades(listadoDeEntidadesSolicitud);
        for(EntidadRespuesta entidad : listadoDeEntidadesRespuesta.result){
            System.out.println(entidad.name);
            System.out.println(entidad.levelOfImpact);
            System.out.println("\n");
        }
    }
}
