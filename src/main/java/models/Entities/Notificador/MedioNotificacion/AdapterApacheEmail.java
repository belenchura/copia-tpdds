package models.Entities.Notificador.MedioNotificacion;
import models.Entities.MonitoreoServicios.Persona.Persona;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.EmailException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AdapterApacheEmail implements AdapterEmail{
    public void notificar(Persona persona, String mensaje){
        Properties properties = new Properties();

        String host = "smtp-relay.brevo.com";
        int port = 587;

        String fromEmail = "tpdiseno4@gmail.com";

        try (FileInputStream fis = new FileInputStream("src/main/resources/Notificador/apacheEmail.properties")) {
            properties.load(fis);
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            Email email = new SimpleEmail();
            email.setHostName(host);
            email.setSmtpPort(port);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setStartTLSEnabled(true);
            email.setFrom(fromEmail);
            email.setSubject("Notificacion Incidente");
            email.setMsg(mensaje);
            email.addTo(persona.getEmail().getEmail());
            email.send();
            System.out.println("El correo electronico fue enviado correctamente.");
        } catch (EmailException | IOException e) {
            System.out.println("Error al enviar el correo electronico: " + e.getMessage());
        }
    }
}