package models.Entities.MonitoreoServicios.Localizacion;

import models.Entities.MonitoreoServicios.Persistente;

import javax.persistence.*;

@Entity
@Table(name = "departamento")
public class Departamento extends Persistente {
    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id")
    private Provincia provincia;

    public Departamento() {
    }
    public Departamento(String nombre, Long id) {
        this.nombre = nombre;
        this.id = id;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}