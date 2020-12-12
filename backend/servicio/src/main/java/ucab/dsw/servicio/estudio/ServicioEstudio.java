package ucab.dsw.servicio.estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstudio extends AplicacionBase {

  @POST
  @Path("/add")
  public Response addEstudio (EstudioDto estudioDto){
    JsonObject data;

    try {

      Estudio estudio = new Estudio();
      estudio.set_estado("procesado");

      Encuesta encuesta = new Encuesta(estudioDto.getEncuesta().getId());
      estudio.set_encuesta(encuesta);

      DaoEstudio daoEstudio = new DaoEstudio();
      Estudio estudioAgregado = daoEstudio.insert(estudio);

      data = Json.createObjectBuilder().add("estudioId", estudioAgregado.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();

    }
    catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return  Response.ok().entity(data).build();
    }

    System.out.println(data);
    return  Response.ok().entity(data).build();

  }
}
