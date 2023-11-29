package models.Entities.Ranking;

import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.Ranking.DataRanking.Item;

import java.util.List;

public interface TipoRanking {
  List<Item> generarRanking(List<Incidente> incidentesSemanales);

}
