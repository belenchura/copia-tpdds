package models.Entities.Notificador;

import models.Entities.MonitoreoServicios.Persistente;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Entities.MonitoreoServicios.Incidente.Incidente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Builder;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "notificacion")
public class Notificacion extends Persistente {
    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona receptor;

    @ManyToOne
    @JoinColumn(name = "incidente_id", referencedColumnName = "id")
    private Incidente incidente;

    @Column(name = "mensaje_a_enviar", columnDefinition = "TEXT")
    private String mensajeAEnviar;

    @Column(name = "es_de_apertura", columnDefinition = "INTEGER")
    private Boolean esDeApertura;

    @Builder.Default
    @Column(name = "fue_enviada", columnDefinition = "INTEGER")
    private Boolean fueEnviada = false;

    @Setter
    @Column(name = "fechayHoraEnviada", columnDefinition = "DATETIME")
    private LocalDateTime fechayHoraEnviada;

    @Column(name = "fechayHoraDebeSerEnviada", columnDefinition = "DATETIME")
    private LocalDateTime fechayHoraDebeSerEnviada;

    public Notificacion(){}
}
