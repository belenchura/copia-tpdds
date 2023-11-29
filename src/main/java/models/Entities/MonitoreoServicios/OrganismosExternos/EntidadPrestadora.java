package models.Entities.MonitoreoServicios.OrganismosExternos;

import lombok.Getter;
import lombok.Setter;
import models.Entities.MonitoreoServicios.Entidad.Entidad;
import models.Entities.MonitoreoServicios.Persistente;
import models.Entities.MonitoreoServicios.Persona.Persona;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "entidad_prestadora")
public class EntidadPrestadora extends Persistente implements OrganismosExternos {
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "entidadPrestadora")
    private List<Entidad> entidadesPrestadas;


    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona personaDesignada;

    public EntidadPrestadora() {}
    public EntidadPrestadora(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void notificarPersona(){}
}