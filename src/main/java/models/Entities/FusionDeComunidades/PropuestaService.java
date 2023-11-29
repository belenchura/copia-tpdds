package models.Entities.FusionDeComunidades;

import models.Entities.FusionDeComunidades.entities.ComunidadServicio1;
import models.Entities.FusionDeComunidades.entities.solicitud.SolicitudPropuesta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

public interface PropuestaService {
    @POST("propose_fusion/")
    Call<List<ComunidadServicio1>> propose_fusion(@Body SolicitudPropuesta solicitudPropuesta);
}
