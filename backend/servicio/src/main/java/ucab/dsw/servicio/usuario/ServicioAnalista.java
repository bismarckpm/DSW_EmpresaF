package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/analista" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAnalista extends AplicacionBase implements IServicioEmpleado{


  @Path("/getsolicitudespendientes/{usuarioAnalistaId}")
  @GET
  public Response getSolicitudesPendientes(@PathParam("usuarioAnalistaId") long id){
    List<SolicitudEstudio> solicitudesPendientes;
    JsonObject data;

    try {

      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      solicitudesPendientes = daoSolicitudEstudio.findAll(SolicitudEstudio.class);

      JsonArrayBuilder solicitudesArray = Json.createArrayBuilder();

      for(SolicitudEstudio solicitudes:solicitudesPendientes) {
        if (solicitudes.get_analista() != null && solicitudes.get_analista().get_id() == id && solicitudes.get_estado().equals("solicitado")) {
          JsonObject solicitudesEstudios = Json.createObjectBuilder().
            add("id", solicitudes.get_id()).
            add("edadInicial", solicitudes.get_edadfinal()).
            add("edadFinal", solicitudes.get_edadfinal()).
            add("genero", solicitudes.get_genero()).
            add("estado", solicitudes.get_estado()).
            add("cliente", solicitudes.get_cliente().get_nombreUsuario()).
            add("marca", solicitudes.get_marca().get_nombreMarca()).
            add("tipoMarca", solicitudes.get_marca().get_tipoMarca()).
            add("capacidadMarca", solicitudes.get_marca().get_capacidad()).
            add("unidadMarca", solicitudes.get_marca().get_unidad()).
            add("unidadSubcategoria", solicitudes.get_marca().get_subcategoria().get_nombreSubcategoria()).
            add("nivelSocioeconomico", solicitudes.get_nivelSocioeconomico().getTipo()).
            add("parroquia", solicitudes.get_parroquia().get_nombreParroquia()).build();

          solicitudesArray.add(solicitudesEstudios);
        }
      }

      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("id", id)
        .add("solicitudes", solicitudesArray).build();

    }catch (NullPointerException ex){
      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("mensaje", "No hay solicitudes pendientes").build();

      System.out.println(data);
      return Response.ok().entity(data).build();

    } catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("code", 400)
        .add("estado", "error").build();

      return Response.ok().entity(data).build();
    }

    System.out.println(data);
    return Response.ok().entity(data).build();
  }

  @Path("/activarestudio/{solicitudId}")
  @PUT
  public Response activarEstudio(@PathParam("solicitudId") long id){

    JsonObject data;

    try {
      SolicitudEstudio solicitud;
      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      solicitud = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

     List<SolicitudEstudio> solicitudesEstudio = daoSolicitudEstudio.getSolicitudesByCaracteristicas(solicitud);

     for(SolicitudEstudio solicitudes:solicitudesEstudio){
        if(solicitudes.get_analista() == solicitud.get_analista() &&
          (solicitudes.get_estado().equals("procesado") || solicitudes.get_estado().equals("ejecutando") || solicitudes.get_estado().equals("culminado"))){
            Estudio estudio = new Estudio(solicitudes.get_estudio().get_id());
            solicitud.set_estudio(estudio);
            solicitud.set_estado("procesado");
            break;
        }
      }

      SolicitudEstudio resultado = daoSolicitudEstudio.update(solicitud);

      System.out.println(solicitud.get_id());

      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("idSolicitud", id)
        .add("estadoSolicitud", resultado.get_estado()).build();

    }
    catch (Exception ex){
      ex.printStackTrace();
      return null;

    }

    System.out.println(data);
    return Response.ok().entity(data).build();
  }
}
