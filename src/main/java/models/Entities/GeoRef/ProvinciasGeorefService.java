package models.Entities.GeoRef;

import models.Entities.GeoRef.entities.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProvinciasGeorefService {

    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("campos") String camposRecibidos);

}

//import GeoRef.services.georef.entities.Provincia;