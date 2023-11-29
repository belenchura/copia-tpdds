package models.Entities.MonitoreoServicios.Localizacion;

import models.Entities.MonitoreoServicios.Persistente;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "provincia")
@Getter
@Setter
public class Provincia extends Persistente {
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "provincia")
    private List<Municipio> municipios;
    @OneToMany(mappedBy = "provincia")
    private List<Departamento> departamentos;

    public Provincia() {
    }
    public Provincia(String nombre, Long id) {
        this.nombre = nombre;
        this.id = id;
        this.departamentos= new ArrayList<>();
        this.municipios= new ArrayList<>();
    }
}