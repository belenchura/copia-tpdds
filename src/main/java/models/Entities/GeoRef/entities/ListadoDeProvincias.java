package models.Entities.GeoRef.entities;

import java.util.List;

public class ListadoDeProvincias {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<ProvinciaGeoRef> provincias;

    private class Parametro {
        public String nombre;
    }
}