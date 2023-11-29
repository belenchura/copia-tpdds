package models.Entities.MonitoreoServicios.Persona;

import lombok.Getter;
import lombok.Setter;
import models.Entities.MonitoreoServicios.Comunidad.Comunidad;
import models.Entities.MonitoreoServicios.Converters.MedioNotificacionConverter;
import models.Entities.MonitoreoServicios.Converters.TipoNotificacionConverter;
import models.Entities.MonitoreoServicios.Interes;
import models.Entities.MonitoreoServicios.Persistente;
import models.Entities.Notificador.MedioNotificacion.MedioNotificacion;
import models.Entities.Notificador.MedioNotificacion.NotificarEmail;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class Persona extends Persistente {
    @ElementCollection
    @CollectionTable(name = "rol_comunidad", joinColumns = @JoinColumn(name = "rol_comunidad_id"))
    @MapKeyColumn(name = "key_column")
    @Column(name = "value_column")
    private Map<String, TipoRol> rolComunidad = new HashMap<>();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_rol")
    private TipoRol tipoRol= TipoRol.USER;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;

    @Embedded
    private Email email;

    @Column(name= "password")
    private String password;

    @Column(name ="salt")
    private String salt= BCrypt.gensalt();

    @Embedded
    private Telefono telefono;

    @OneToOne
    @JoinColumn(name = "interes_id", referencedColumnName = "id")
    private Interes interes;

    @Transient
    private UbicacionActual ubicacionActual;

    @Convert(converter = MedioNotificacionConverter.class)
    @Column(name = "medio_notificacion")
    private MedioNotificacion medioNotificacion=new NotificarEmail();

    @ElementCollection
    @CollectionTable(name = "horario_notificacion", joinColumns =  @JoinColumn(name = "persona_id"))
    @Column (name = "horario_notificacion")
    private List<LocalTime> horariosNotificacion;

    @Convert(converter = TipoNotificacionConverter.class)
    @Column(name = "tipo_notificacion")
    private TipoNotificacion tipoNotificacion= new CuandoSucede();

    public Persona() {
    }
    public Persona(String nombre, String apellido, Email email, Telefono telefono, Interes interes) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.interes = interes;
    }

}
