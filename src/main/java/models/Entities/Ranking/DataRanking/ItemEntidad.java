package models.Entities.Ranking.DataRanking;

import models.Entities.MonitoreoServicios.Entidad.Entidad;

public class ItemEntidad implements ItemRanking{

  public Entidad entidad;
  public double diferencia;

  public ItemEntidad(Entidad entidad, double diferencia) {
    this.entidad = entidad;
    this.diferencia = diferencia;
  }

  public Entidad getEntidad() {
    return entidad;
  }

  public double getDiferencia(){return diferencia;}
  public Item getItem(){
    return new Item(entidad.getNombre(),diferencia);
  }
}
