package models.Entities.GeoRef.entities;

import java.util.List;

public class ListadoDeMunicipios {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<MunicipioGeoRef> municipios;

    private class Parametro {
        public List<String> campos;
        public String provincia;
    }
}