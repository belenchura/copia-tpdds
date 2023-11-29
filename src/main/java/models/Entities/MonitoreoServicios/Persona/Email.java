package models.Entities.MonitoreoServicios.Persona;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Email {
    @Column(name = "email")
    private String email;
    public Email(String email) {
        this.email = email;
    }
    public Email() {}

    public Boolean verificarEmail(String email) {
        return email.matches(".*@(gmail\\.com|hotmail\\.com|yahoo\\.com|frba\\.utn\\.edu\\.ar)$");
    }

    public String getEmail() {
        return this.email;
    }
}
