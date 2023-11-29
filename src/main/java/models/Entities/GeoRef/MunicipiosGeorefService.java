package models.Entities.GeoRef;

import models.Entities.GeoRef.entities.ListadoDeMunicipios;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MunicipiosGeorefService {

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") String provincia, @Query("campos") String camposRecibidos);
}
