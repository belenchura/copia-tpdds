package models.Entities.MonitoreoServicios.Establecimiento;

import models.Entities.MonitoreoServicios.Entidad.Entidad;
import models.Entities.MonitoreoServicios.Localizacion.Departamento;
import models.Entities.MonitoreoServicios.Localizacion.Localidad;
import models.Entities.MonitoreoServicios.Localizacion.Municipio;
import models.Entities.MonitoreoServicios.Localizacion.Provincia;
import models.Entities.MonitoreoServicios.Persistente;
import models.Entities.MonitoreoServicios.Prestacion;
import models.Entities.MonitoreoServicios.Servicio.Servicio;
import models.Entities.MonitoreoServicios.Localizacion.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
@Entity

@Table(name = "establecimiento")

public class Establecimiento extends Persistente {
    @Column(name = "nombre")
    private String nombre;
    @Getter
    @ManyToOne
    @JoinColumn(name = "entidad_id", referencedColumnName = "id")
    private Entidad entidad;

    @ManyToOne
    @JoinColumn(name = "tipo_establecimiento_id", referencedColumnName = "id")
    private TipoEstablecimiento tipo;

    @OneToMany(mappedBy = "establecimiento")
    private List<Prestacion> prestaciones;

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

    public Establecimiento() {
    }
    public Establecimiento(TipoEstablecimiento tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public List<Servicio> obtenerServiciosActivos(){
        return this.prestaciones.stream().filter(p->p.getEstado()).map(p->p.getServicio()).collect(Collectors.toList());
    }

    public String getNombre() {
        return nombre;
    }

    public List<Prestacion> getPrestaciones() {
        return prestaciones;
    }
}