package models.Entities.Ranking.DataRanking;

import java.util.List;
import java.util.stream.Collectors;

public class ResultEntidades implements ResultRanking {
  public List<ItemEntidad> items;

  public void add(ItemEntidad item){
    if(items.stream().anyMatch(i -> i.getEntidad().equals(item.getEntidad()))){
        // Si existe, buscarlo y sumar al promedio de rta
      items.add(item);
    }
    else{
      items.add(item);
    }
  }

  public List<Item> getDataRanking(){
    return this.items.stream().map(i -> i.getItem()).collect(Collectors.toList());
  }

}
