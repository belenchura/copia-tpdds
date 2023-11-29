package models.Entities.MonitoreoServicios;
import lombok.Getter;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public abstract class Persistente {
    @Id
    @GeneratedValue
    protected Long id;
}
