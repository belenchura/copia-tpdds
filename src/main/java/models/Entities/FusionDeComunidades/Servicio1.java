package models.Entities.FusionDeComunidades;

import models.Entities.FusionDeComunidades.entities.ComunidadServicio1;
import models.Entities.FusionDeComunidades.entities.SolicitudFusion;
import models.Entities.FusionDeComunidades.entities.solicitud.SolicitudPropuesta;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class Servicio1 {
    private static Servicio1 instancia = null;
    private static final String urlApi = "https://api-python-ppz7.onrender.com/";
    private Retrofit retrofit;

    private Servicio1() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Servicio1 instancia() {
        if (instancia == null) {
            instancia = new Servicio1();
        }
        return instancia;
    }

    public List<ComunidadServicio1> propose_fusion(SolicitudPropuesta solicitud) throws IOException {
        PropuestaService propuestaService = this.retrofit.create(PropuestaService.class);
        Call<List<ComunidadServicio1>> requestComunidades = propuestaService.propose_fusion(solicitud);
        Response<List<ComunidadServicio1>> responseComunidades = requestComunidades.execute();
        return responseComunidades.body();
    }
    public ComunidadServicio1 fuse_communities(SolicitudFusion solicitud) throws IOException {
        FusionService fusionService = this.retrofit.create(FusionService.class);
        Call<ComunidadServicio1> requestComunidad = fusionService.fuse_communities(solicitud);
        Response<ComunidadServicio1> responseComunidad = requestComunidad.execute();
        return responseComunidad.body();
    }
}
