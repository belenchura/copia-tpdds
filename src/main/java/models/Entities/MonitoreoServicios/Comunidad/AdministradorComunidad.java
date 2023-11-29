package models.Entities.MonitoreoServicios.Comunidad;

import models.Entities.MonitoreoServicios.Interes;
import models.Entities.MonitoreoServicios.Persistente;
import models.Entities.MonitoreoServicios.Persona.Persona;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "administrador_comunidad")
public class AdministradorComunidad extends Persistente {
    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;
    @ManyToOne
    @JoinColumn(name = "interes_id", referencedColumnName = "id")
    private Interes interes;

    public AdministradorComunidad() {
    }
    public AdministradorComunidad(Persona persona, Interes interes) {
        this.persona = persona;
        this.interes = interes;
    }
}