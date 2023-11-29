package models.Entities.GeoRef.entities;

import java.util.List;

public class ListadoDeDepartamentos {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<DepartamentoGeoref> departamentos;

    private class Parametro {
        public List<String> campos;
        public String provincia;
    }
}
