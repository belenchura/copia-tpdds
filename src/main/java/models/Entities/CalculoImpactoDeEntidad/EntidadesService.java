package models.Entities.CalculoImpactoDeEntidad;

import models.Entities.CalculoImpactoDeEntidad.entities.respuesta.ListadoDeEntidadesRespuesta;
import models.Entities.CalculoImpactoDeEntidad.entities.solicitud.ListadoDeEntidadesSolicitud;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EntidadesService {
    @POST("calculate_ranking")
    Call<ListadoDeEntidadesRespuesta> entidades(@Body ListadoDeEntidadesSolicitud listadoDeEntidadesSolicitud);
}
