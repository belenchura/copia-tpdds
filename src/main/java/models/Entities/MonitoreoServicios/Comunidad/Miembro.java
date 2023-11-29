package models.Entities.MonitoreoServicios.Comunidad;

import lombok.Getter;
import lombok.Setter;
import models.Entities.MonitoreoServicios.Interes;
import models.Entities.MonitoreoServicios.Persistente;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Entities.MonitoreoServicios.Prestacion;

import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.MonitoreoServicios.Servicio.Servicio;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Getter
@Setter
@Entity
@Table(name = "miembro")
public class Miembro extends Persistente {
    @Column(name = "estado", columnDefinition = "INTEGER")
    private Boolean estado;//true si el incidente está abierto, false si el incidente está cerrado

    @ManyToOne //TODO
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "interes_id", referencedColumnName = "id")
    private Interes interes;

    @ManyToOne
    @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
    private Comunidad comunidad;

    @OneToMany(mappedBy = "miembro")
    private List<ServicioMiembro> servicioMiembro;

    public Miembro(Persona persona) {
        this.persona = persona;

    }

    public Miembro() {
    }

    public Persona getPersona() {
        return persona;
    }
    public void abrirIncidente(Prestacion prestacion, String observaciones){
        Incidente incidente = new Incidente.IncidenteBuilder()
                .setObservaciones(observaciones)
                .setComunidad(this.comunidad)
                .setPrestacion(prestacion)
                .build();

        //Notificador.notificarAperturaOCierreIncidente(incidente);
    }

    public void cerrarIncidente(Incidente incidente){
        incidente.setEstado(false);
        incidente.setFechaYHoraCierre(LocalDateTime.now());
        //Notificador.notificarAperturaOCierreIncidente(incidente);
    }

    public void cambiarTipoMiembro(Servicio servicio, TipoMiembro tipoMiembro) {
        Optional<ServicioMiembro> optionalServicioMiembro = this.servicioMiembro.stream()
                .filter(sm -> sm.getServicio().equals(servicio))
                .findFirst();

        optionalServicioMiembro.ifPresent(sm -> sm.setTipoMiembro(tipoMiembro));
    }

}