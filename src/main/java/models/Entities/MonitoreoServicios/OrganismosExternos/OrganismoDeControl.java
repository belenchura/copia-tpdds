package models.Entities.MonitoreoServicios.OrganismosExternos;

import lombok.Getter;
import lombok.Setter;
import models.Entities.MonitoreoServicios.Localizacion.Departamento;
import models.Entities.MonitoreoServicios.Localizacion.Localidad;
import models.Entities.MonitoreoServicios.Localizacion.Municipio;
import models.Entities.MonitoreoServicios.Localizacion.Provincia;
import models.Entities.MonitoreoServicios.Persistente;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Entities.MonitoreoServicios.Servicio.Servicio;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "organismo_control")
public class OrganismoDeControl extends Persistente implements OrganismosExternos {
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "organismoDeControl")
    private List<Servicio> serviciosControlados;

    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona personaDesignada;

    @ManyToOne
    @JoinColumn(name = "provincia_id",referencedColumnName = "id" )
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "municipio_id", referencedColumnName = "id")
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "localidad_id", referencedColumnName = "id")
    private Localidad localidad;

    public String getNombre() {
        return this.nombre;
    }
    public OrganismoDeControl() {}

    public OrganismoDeControl(String nombre) {
        this.nombre = nombre;
    }
    public void notificarPersona(){}
}