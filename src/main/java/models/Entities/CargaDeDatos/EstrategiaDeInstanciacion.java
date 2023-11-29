package models.Entities.CargaDeDatos;
import java.util.*;

import models.Entities.MonitoreoServicios.OrganismosExternos.OrganismosExternos;

public interface EstrategiaDeInstanciacion {
    public List<OrganismosExternos> instanciar(List<String[]> lineas);
}
