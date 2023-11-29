package models.Entities.MonitoreoServicios.Localizacion;

import models.Entities.MonitoreoServicios.Persistente;

import javax.persistence.*;

@Entity
@Table(name = "localidad")
public class Localidad extends Persistente {
    @Column(name = "nombre")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id")
    private Provincia provincia;
    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    private Departamento departamento;
    @ManyToOne
    @JoinColumn(name = "municipio_id", referencedColumnName = "id")
    private Municipio municipio;

  public Localidad(){
  }
    public Localidad(String nombre,Long id, Provincia provincia,Municipio municipio){
            this.nombre=nombre;
            this.id=id;
            this.provincia=provincia;
            this.municipio=municipio;
    }
}