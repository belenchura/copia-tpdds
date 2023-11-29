package models.Entities.Ranking.DataRanking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
  public String nombre;
  public double valor;

  public Item(String nombre, double valor) {
    this.nombre = nombre;
    this.valor = valor;
  }



}
