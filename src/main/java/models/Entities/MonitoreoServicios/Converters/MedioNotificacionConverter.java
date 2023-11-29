package models.Entities.MonitoreoServicios.Converters;

import models.Entities.Notificador.MedioNotificacion.MedioNotificacion;
import models.Entities.Notificador.MedioNotificacion.NotificarEmail;
import models.Entities.Notificador.MedioNotificacion.NotificarWhatsapp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MedioNotificacionConverter implements AttributeConverter<MedioNotificacion, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MedioNotificacion medioNotificacion){
        String nombre = medioNotificacion.getClass().getName();
        return nombre.equals("NotificarEmail") ? 0 : 1;
    } //persistir, desde java a la base
    @Override
    public MedioNotificacion convertToEntityAttribute(Integer num) {
        MedioNotificacion medio = null;
        if(num==0){medio= new NotificarEmail();}
        if(num==1){medio= new NotificarWhatsapp();}
        return medio;
    } //recuperando, desde la base hacia java
}