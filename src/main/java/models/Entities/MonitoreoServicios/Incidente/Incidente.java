package models.Entities.MonitoreoServicios.Incidente;

import models.Entities.MonitoreoServicios.Comunidad.Comunidad;
import models.Entities.MonitoreoServicios.Comunidad.Miembro;
import models.Entities.MonitoreoServicios.Persistente;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Entities.MonitoreoServicios.Prestacion;
import lombok.Setter;
import lombok.Getter;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "incidente")
public class Incidente extends Persistente {

    @Column(name = "estado", columnDefinition = "INTEGER")
    private Boolean estado;//true si el incidente está abierto, false si el incidente está cerrado

    @ManyToOne
    @JoinColumn(name = "prestacion_id", referencedColumnName = "id")
    private Prestacion prestacion;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "fechaYHoraApertura", columnDefinition = "DATETIME")
    private LocalDateTime fechaYHoraApertura;

    @Column(name = "fechaYHoraCierre", columnDefinition = "DATETIME")
    private LocalDateTime fechaYHoraCierre;

    @ManyToOne
    @JoinColumn(name = "persona_apertura_id", referencedColumnName = "id")
    private Persona personaApertura;

    @ManyToOne
    @JoinColumn(name = "persona_cierre_id", referencedColumnName = "id")
    private Persona personaCierre;

    @ManyToOne
    @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
    private Comunidad comunidad;

    public Incidente() {
    }

    private Incidente(IncidenteBuilder incidenteBuilder) {
        this.fechaYHoraApertura = LocalDateTime.now();
        this.prestacion = incidenteBuilder.prestacion;
        this.estado = true;
        this.personaApertura = incidenteBuilder.personaApertura;
        this.comunidad = incidenteBuilder.comunidad;
        this.observaciones = incidenteBuilder.observaciones;
        this.fechaYHoraCierre=null;
    }

    public double calcularDuracionCierreEnMinutos() {
        if(!this.getEstado()){ // incidente cerrado
            LocalDateTime apertura = this.getFechaYHoraApertura();
            LocalDateTime cierre = this.getFechaYHoraCierre();
            return Duration.between(apertura, cierre).toMinutes();
        }
        else{
            return 0;
        }
    }

    public static class IncidenteBuilder {
        private Boolean estado;
        private Prestacion prestacion;
        private String observaciones;
        private Persona personaApertura;
        private Comunidad comunidad;

        public IncidenteBuilder setMiembroApertura(Persona personaApertura) {
            this.personaApertura = personaApertura;
            return this;
        }

        public IncidenteBuilder setObservaciones(String observaciones) {
            this.observaciones = observaciones;
            return this;
        }

        public IncidenteBuilder setPrestacion(Prestacion prestacion) {
            this.prestacion = prestacion;
            return this;
        }

        public IncidenteBuilder setComunidad(Comunidad comunidad) {
            this.comunidad = comunidad;
            return this;
        }



        public Incidente build() {
            return new Incidente(this);
        }
    }
}