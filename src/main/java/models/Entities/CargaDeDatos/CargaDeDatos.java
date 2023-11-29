package models.Entities.CargaDeDatos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import models.Entities.MonitoreoServicios.OrganismosExternos.OrganismosExternos;

public class CargaDeDatos {

    @Getter
    @Setter
    private AdapterLectorCSV adapterLectorCSV;
    private EstrategiaDeInstanciacion estrategia;

    public void cambiarEstrategia(EstrategiaDeInstanciacion estrategia){
        this.estrategia = estrategia;
    }

    public  List<OrganismosExternos> cargarDatos(String path) {
        List<String[]> lineas=this.adapterLectorCSV.leerLineas(path);
        return this.estrategia.instanciar(lineas);
    }
}