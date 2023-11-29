package models.Entities.FusionDeComunidades;

import models.Entities.FusionDeComunidades.entities.ComunidadServicio1;
import models.Entities.FusionDeComunidades.entities.SolicitudFusion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FusionService {
    @POST("fuse_communities/")
    Call<ComunidadServicio1> fuse_communities(@Body SolicitudFusion solicitudFusion);
}
