package models.Entities.CalculoImpactoDeEntidad;

import models.Entities.CalculoImpactoDeEntidad.entities.respuesta.ListadoDeEntidadesRespuesta;
import models.Entities.CalculoImpactoDeEntidad.entities.solicitud.ListadoDeEntidadesSolicitud;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Servicio3 {
    private static Servicio3 instancia = null;
    private static final String urlApi = "https://servicio-dds-rankings-dev.fl0.io/";
    private Retrofit retrofit;

    private Servicio3() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Servicio3 instancia() {
        if (instancia == null) {
            instancia = new Servicio3();
        }
        return instancia;
    }

    public ListadoDeEntidadesRespuesta listadoDeEntidades(ListadoDeEntidadesSolicitud listadoDeEntidadesSolicitud) throws IOException {
        EntidadesService entidadService = this.retrofit.create(EntidadesService.class);
        Call<ListadoDeEntidadesRespuesta> requestEntidades = entidadService.entidades(listadoDeEntidadesSolicitud);
        Response<ListadoDeEntidadesRespuesta> responseEntidades = requestEntidades.execute();
        return responseEntidades.body();
    }
}