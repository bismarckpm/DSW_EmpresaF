package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/administrador" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAdministrador extends AplicacionBase implements IServicioEmpleado {

  @GET
  @Path("/getsolicitudespendientes/{usuarioAdministradorId}")
  public Response getSolicitudesPendientes(@PathParam("usuarioAdministradorId") long id){
    List<SolicitudEstudio> solicitudesPendientes;
    JsonObject data;

    try {

      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      solicitudesPendientes = daoSolicitudEstudio.findAll(SolicitudEstudio.class);

      JsonArrayBuilder solicitudesArray = Json.createArrayBuilder();

      for(SolicitudEstudio solicitudes:solicitudesPendientes) {
        if (solicitudes.get_administrador() != null && solicitudes.get_administrador().get_id() == id && solicitudes.get_estado().equals("solicitado")) {
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

    }catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("mensaje", ex.getMessage()).build();

      System.out.println(data);
      return Response.ok().entity(data).build();

    }

    System.out.println(data);
    return Response.ok().entity(data).build();
  }


  @PUT
  @Path("/asignarsolicitud/{idSolicitud}")
  public Response asignarEstudioASolicitud(@PathParam("idSolicitud") long id, SolicitudEstudioDto solicitudEstudioDto){
    JsonObject data;

    try{
      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

      Estudio estudio = new Estudio(solicitudEstudioDto.getEstudio().getId());
      solicitudEstudio.set_estudio(estudio);
      solicitudEstudio.set_estado("procesado");

      int random = (int) (Math.random()*(82-81+1)+81);
      Usuario analista = new Usuario(random);
      solicitudEstudio.set_analista(analista);
      SolicitudEstudio resultado = daoSolicitudEstudio.update(solicitudEstudio);

      data = Json.createObjectBuilder().
        add("id", resultado.get_id())
        .add("estado", "success")
        .add("code", 200).build();

      System.out.println(data);
      return Response.ok().entity(data).build();

    }catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400).build();

      System.out.println(data);
      return Response.ok().entity(data).build();
    }
  }
}
