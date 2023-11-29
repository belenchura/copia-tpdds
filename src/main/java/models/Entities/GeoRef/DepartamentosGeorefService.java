package models.Entities.GeoRef;

import models.Entities.GeoRef.entities.ListadoDeDepartamentos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DepartamentosGeorefService {

    @GET("departamentos")
    Call<ListadoDeDepartamentos> departamentos(@Query("provincia") String provincia, @Query("campos") String camposRecibidos);
}
