package models.Entities.MonitoreoServicios.Servicio;

import lombok.Getter;
import lombok.Setter;
import models.Entities.MonitoreoServicios.OrganismosExternos.OrganismoDeControl;
import models.Entities.MonitoreoServicios.Persistente;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "servicio")
@Getter
@Setter
public class Servicio extends Persistente {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private List<Servicio> servicios;

    @ManyToOne
    @JoinColumn(name = "organismo_control_id", referencedColumnName = "id")
    private OrganismoDeControl organismoDeControl;

    public Servicio() {
    }
    public Servicio(String nombre, String descripcion, List<Servicio> servicios) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}
