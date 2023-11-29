package models.Entities.CargaDeDatos;


import models.Entities.MonitoreoServicios.OrganismosExternos.OrganismoDeControl;
import models.Entities.MonitoreoServicios.OrganismosExternos.OrganismosExternos;
import models.Entities.MonitoreoServicios.OrganismosExternos.*;
import models.Entities.MonitoreoServicios.Persona.Email;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Repositories.RepositorioTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InstanciarOrganismoDeControl implements EstrategiaDeInstanciacion {
    RepositorioTemplate<Persona> repoPersonas;
    public  InstanciarOrganismoDeControl(RepositorioTemplate<Persona> repoPersonas){
        this.repoPersonas=repoPersonas;
    }
    public List<OrganismosExternos> instanciar(List<String[]> lineas) {
        List<OrganismosExternos> listaOrganismos=new ArrayList<>();

        for(String[] linea:lineas) {
            OrganismoDeControl organismoDeControl=new OrganismoDeControl(linea[0]);
            Optional<Persona> personaDesignada= repoPersonas.buscarTodos().stream().filter(persona -> persona.getEmail().getEmail().equals(linea[3])).findAny();
            organismoDeControl.setPersonaDesignada(personaDesignada.orElse(new Persona(linea[1],linea[2],new Email(linea[3]),null,null)));
            listaOrganismos.add( organismoDeControl);
        }
        return listaOrganismos;
    }
}
