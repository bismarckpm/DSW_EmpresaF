package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/administrador" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAdministrador extends AplicacionBase implements IServicioEmpleado {

  @Path("/getsolicitudespendientes/{id}")
  public Response getSolicitudesPendientes(@PathParam("id") long id){
    List<SolicitudEstudio> solicitudesPendientes;
    JsonObject data=null;

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
}
