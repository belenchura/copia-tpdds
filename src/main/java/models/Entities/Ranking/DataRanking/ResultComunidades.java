package models.Entities.Ranking.DataRanking;

import java.util.List;
import java.util.stream.Collectors;

public class ResultComunidades implements ResultRanking {
  public List<ItemComunidad> items;

  public void add(ItemComunidad item){
    if(items.stream().anyMatch(i -> i.getComunidad().equals(item.getComunidad()))){
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
