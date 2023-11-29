package models.Entities.GeoRef;

import models.Entities.GeoRef.entities.ListadoDeLocalidades;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalidadesGeorefService {
    @GET("localidades")
    Call<ListadoDeLocalidades> localidades(@Query("provincia") String provincia, @Query("municipio") String nombreMunicipio, @Query("campos") String camposRecibidos);
}
