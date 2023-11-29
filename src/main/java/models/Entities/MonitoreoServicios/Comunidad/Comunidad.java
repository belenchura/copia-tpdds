package models.Entities.MonitoreoServicios.Comunidad;

import lombok.Getter;
import lombok.Setter;
import models.Entities.MonitoreoServicios.Persistente;
import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.MonitoreoServicios.Servicio.Servicio;

import javax.persistence.*;
import java.util.*;
@Getter
@Setter
@Entity
@Table(name = "comunidad")
public class Comunidad extends Persistente {

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "comunidad",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Miembro> miembros;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comunidad", referencedColumnName = "id")
    private List<AdministradorComunidad> administradores;

    @ManyToMany
    private List<Servicio> serviciosInteres;
    @OneToMany(mappedBy = "comunidad")
    private List<Incidente> incidentes;

    public Comunidad(List<Servicio> serviciosInteres) {
        this.miembros = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.serviciosInteres = new ArrayList<>();
    }
    public Comunidad() {
    }
    public List<Miembro> getMiembros() {
        return miembros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setMiembros(Miembro miembro) {
        this.miembros.add(miembro);
    }
}