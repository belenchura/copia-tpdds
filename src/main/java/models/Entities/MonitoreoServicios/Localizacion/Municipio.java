package models.Entities.MonitoreoServicios.Localizacion;

import models.Entities.MonitoreoServicios.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "municipio")
@Getter
@Setter
public class Municipio extends Persistente {
    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id")
    private Provincia provincia;

    @OneToMany(mappedBy = "municipio")
    private List<Localidad> localidades;

    public Municipio(String nombre, Long id,Provincia provincia) {
        this.nombre = nombre;
        this.id = id;
        this.provincia=provincia;
    }
    public Municipio() {
    }
}