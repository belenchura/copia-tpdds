package GeoRef;

import models.Entities.GeoRef.ServicioGeoref;
import models.Entities.GeoRef.entities.ListadoDeLocalidades;
import models.Entities.GeoRef.entities.MunicipioGeoRef;
import models.Entities.GeoRef.entities.ProvinciaGeoRef;

import java.io.IOException;

public class EjemploDeUsoLocalidad {
    public static void main(String[] args) throws IOException {
        MunicipioGeoRef municipio;
        ProvinciaGeoRef Jujuy;

        Jujuy = new ProvinciaGeoRef();
        Jujuy.nombre = "Jujuy";
        municipio = new MunicipioGeoRef();
        municipio.nombre = "Yavi";
        municipio.provincia = Jujuy;

        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();
        ListadoDeLocalidades localidades= servicioGeoref.listadoDeLocalidadesDeMunicipio(municipio);
        localidades.localidades.forEach(l->System.out.println(l.nombre));
    }
}