package models.Entities.Ranking.DataRanking;

import models.Entities.MonitoreoServicios.Comunidad.Comunidad;

public class ItemComunidad implements ItemRanking{
  public Comunidad comunidad;
  public double diferencia;

  public ItemComunidad(Comunidad entidad, double diferencia) {
    this.comunidad = entidad;
    this.diferencia = diferencia;
  }

  public Comunidad getComunidad() {
    return comunidad;
  }

  public Item getItem(){
    return new Item(comunidad.getNombre(),diferencia);
  }

}
