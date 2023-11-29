package Notificador;

import models.Entities.MonitoreoServicios.Persona.Email;
import models.Entities.MonitoreoServicios.Interes;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Entities.MonitoreoServicios.Persona.Telefono;
import models.Entities.Notificador.MedioNotificacion.AdapterTwilio;

import java.io.IOException;

public class AdapterTwilioTest {
        public static void main(String[] args) throws IOException {
            AdapterTwilio adapterTwilio = new AdapterTwilio();
            Email email = new Email("carlosdiaz@gmail.com");
            Telefono telefono= new Telefono("+5491149464277");
            Persona persona = new Persona("carlos","diaz",email,telefono,new Interes());
            adapterTwilio.notificar(persona, "Mensaje de prueba");
        }
}