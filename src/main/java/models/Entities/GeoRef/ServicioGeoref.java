package models.Entities.GeoRef;

import models.Entities.GeoRef.entities.*;



import models.Entities.GeoRef.entities.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoref {
    private static ServicioGeoref instancia = null;
    private static final String urlApi = "https://apis.datos.gob.ar/georef/api/";
    private Retrofit retrofit;

    private ServicioGeoref() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioGeoref instancia(){
        if(instancia == null){
            instancia = new ServicioGeoref();
        }
        return instancia;
    }

    public ListadoDeProvincias listadoDeProvincias() throws IOException {
        ProvinciasGeorefService provinciaGeorefService = this.retrofit.create(ProvinciasGeorefService.class);
        Call<ListadoDeProvincias> requestProvinciasArgentinas = provinciaGeorefService.provincias("id, nombre");
        Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        return responseProvinciasArgentinas.body();
    }

    public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(ProvinciaGeoRef provincia) throws IOException {
        MunicipiosGeorefService municipioGeorefService = this.retrofit.create(MunicipiosGeorefService.class);
        Call<ListadoDeMunicipios> requestListadoDeMunicipios = municipioGeorefService.municipios(provincia.nombre, "id, nombre, provincia");
        Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        return responseListadoDeMunicipios.body();
    }
    public ListadoDeLocalidades listadoDeLocalidadesDeMunicipio(MunicipioGeoRef municipio) throws IOException {
        LocalidadesGeorefService localidadesGeorefService = this.retrofit.create(LocalidadesGeorefService.class);
        Call<ListadoDeLocalidades> requestListadoDeLocalidades = localidadesGeorefService.localidades(municipio.provincia.nombre,municipio.nombre,"id, nombre, provincia, municipio");
        Response<ListadoDeLocalidades> responseListadoDeLocalidades = requestListadoDeLocalidades.execute();
        return responseListadoDeLocalidades.body();
    }

    public ListadoDeDepartamentos listadoDeDepartamentos(ProvinciaGeoRef provincia) throws IOException {
        DepartamentosGeorefService departamentosGeorefService = this.retrofit.create(DepartamentosGeorefService.class);
        Call<ListadoDeDepartamentos> requestDepartamentosArgentinas = departamentosGeorefService.departamentos(provincia.nombre,"id, nombre");
        Response<ListadoDeDepartamentos> responseDepartamentosArgentinas = requestDepartamentosArgentinas.execute();
        return responseDepartamentosArgentinas.body();
    }
}