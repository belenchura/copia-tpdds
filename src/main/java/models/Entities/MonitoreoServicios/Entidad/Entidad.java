package models.Entities.MonitoreoServicios.Entidad;

import lombok.Getter;
import lombok.Setter;
import models.Entities.MonitoreoServicios.Establecimiento.Establecimiento;
import models.Entities.MonitoreoServicios.Localizacion.Departamento;
import models.Entities.MonitoreoServicios.Localizacion.Localidad;
import models.Entities.MonitoreoServicios.Localizacion.Municipio;
import models.Entities.MonitoreoServicios.Localizacion.Provincia;
import models.Entities.MonitoreoServicios.OrganismosExternos.EntidadPrestadora;
import models.Entities.MonitoreoServicios.Localizacion.*;
import models.Entities.MonitoreoServicios.Persistente;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "entidad")
public class Entidad extends Persistente {
    @Column(name = "nombre")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "tipo_entidad_id", referencedColumnName = "id")
    private TipoEntidad tipo;

    @OneToMany(mappedBy = "entidad")
    private List<Establecimiento> establecimientos;

    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id")
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

    @ManyToOne
    @JoinColumn(name = "entidad_prestadora_id", referencedColumnName = "id")
    private EntidadPrestadora entidadPrestadora;

    public Entidad() {
    }
    public Entidad(TipoEntidad tipo, String nombre, List<Establecimiento> paradas) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.establecimientos = paradas;
    }

    public List<Establecimiento> getEstablecimientos() {
        return establecimientos;
    }

    public String getNombre() {
        return nombre;
    }
}
