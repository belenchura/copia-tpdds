package models.Entities.MonitoreoServicios.Converters;

import models.Entities.MonitoreoServicios.Persona.CuandoSucede;
import models.Entities.MonitoreoServicios.Persona.SinApuros;
import models.Entities.MonitoreoServicios.Persona.TipoNotificacion;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoNotificacionConverter implements AttributeConverter<TipoNotificacion, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoNotificacion tipoNotificacion){
        String nombre = tipoNotificacion.getClass().getName();
        return nombre == "SinApuros" ? 0 : 1;
    } //persistir, desde java a la base
    @Override
    public TipoNotificacion convertToEntityAttribute(Integer num) {
        TipoNotificacion tipo = null;
        if(num==0){tipo = new SinApuros();}
        if(num==1){tipo = new CuandoSucede();}
        return tipo;
    } //recuperando, desde la base hacia java
}
