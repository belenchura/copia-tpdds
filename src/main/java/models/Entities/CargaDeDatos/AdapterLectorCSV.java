package models.Entities.CargaDeDatos;

import java.util.List;

public interface AdapterLectorCSV {
    public List<String[]> leerLineas(String path);
}
