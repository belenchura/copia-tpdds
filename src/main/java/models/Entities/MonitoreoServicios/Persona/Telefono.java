package models.Entities.MonitoreoServicios.Persona;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Telefono {
    @Column(name = "telefono")
    private String telefono;
    public Boolean verificarTelefono(String telefono) {
        return telefono.matches("\\+54911\\d{8}");
    }
    public Telefono(String telefono){
        this.telefono=telefono;
    }
    public Telefono(){}
    public String getTelefono() {
        return this.telefono;
    }
}
