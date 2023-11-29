package models.Entities.Notificador.MedioNotificacion;

import models.Entities.MonitoreoServicios.Persona.Persona;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AdapterTwilio implements AdapterWhatsapp{
    private String ACCOUNT_SID;
    private String AUTH_TOKEN;
    public void notificar(Persona persona, String mensaje) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/Notificador/twilio.properties")) {
            properties.load(fis);

            this.ACCOUNT_SID = properties.getProperty("ACCOUNT_SID");
            this.AUTH_TOKEN = properties.getProperty("AUTH_TOKEN");
            System.out.println(this.ACCOUNT_SID);
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("whatsapp:"+persona.getTelefono()),
                            new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                            mensaje)
                    .create();
            System.out.println(message.getSid());

        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
    }
}