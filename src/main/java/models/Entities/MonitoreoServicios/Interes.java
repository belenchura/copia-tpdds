package models.Entities.MonitoreoServicios;

import models.Entities.MonitoreoServicios.Entidad.Entidad;
import models.Entities.MonitoreoServicios.Localizacion.Departamento;
import models.Entities.MonitoreoServicios.Localizacion.Localidad;
import models.Entities.MonitoreoServicios.Localizacion.Municipio;
import models.Entities.MonitoreoServicios.Localizacion.Provincia;
import models.Entities.MonitoreoServicios.Servicio.Servicio;
import models.Repositories.RepositorioTemplate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "interes")
public class Interes extends Persistente{
    @ManyToMany
    private List<Entidad> entidades;
    @ManyToMany
    private List<Servicio> servicios;

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

    public Interes() {
        this.entidades = new ArrayList<>();
        this.servicios = new ArrayList<>();
    }
    public List<Prestacion> obtenerPrestacionesDeInteres(List<Prestacion> repoPrestaciones){
        List<Prestacion> prestaciones=new ArrayList<>();
        prestaciones.addAll(repoPrestaciones.stream()
                .filter(prestacion -> this.servicios.contains(prestacion.getServicio())
                        && this.entidades.contains(prestacion.getEstablecimiento().getEntidad())).collect(Collectors.toList()));
        return prestaciones;
    }
    public void setEntidades(List<Entidad> entidadesRecibidas){
        this.entidades.addAll(entidadesRecibidas);
    }
    public void setEntidades(Entidad entidadesRecibidas){
        this.entidades.add(entidadesRecibidas);
    }
    public void setServicios(List<Servicio> serviciosRecibidos){
        this.servicios.addAll(serviciosRecibidos);
    }
    public void setServicios(Servicio serviciosRecibidos){
        this.servicios.add(serviciosRecibidos);
    }

    public List<Servicio> getServicios() {
        return servicios;
    }
}