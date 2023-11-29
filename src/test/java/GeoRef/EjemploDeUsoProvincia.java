package GeoRef;

import models.Entities.GeoRef.ServicioGeoref;
import models.Entities.GeoRef.entities.ListadoDeProvincias;
import models.Entities.GeoRef.entities.ProvinciaGeoRef;

import java.io.IOException;

public class EjemploDeUsoProvincia {

    /*public static void main(String[] args) throws IOException {
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

        System.out.println("Introduzca el nombre de una provincia: ");
        Scanner entradaEscaner = new Scanner(System.in);
        String nombreProvincia = entradaEscaner.nextLine();
        ListadoDeProvincias listadoDeProvinciasArgentinas = servicioGeoref.listadoDeProvincias(nombreProvincia);
        System.out.println(listadoDeProvinciasArgentinas.provincias.get(0).id);
    }*/

    public static void main(String[] args) throws IOException {
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

        ListadoDeProvincias listadoDeProvinciasArgentinas = servicioGeoref.listadoDeProvincias();
        for(ProvinciaGeoRef provincia : listadoDeProvinciasArgentinas.provincias){
            System.out.println(provincia.nombre);
        }
    }
}