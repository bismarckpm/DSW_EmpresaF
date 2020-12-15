package ucab.dsw.servicio.estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

  @GET
  @Path("/getall")
  public Response getEstudios(){
    JsonObject data;

    try {

      DaoEstudio daoEstudio = new DaoEstudio();
      List<Estudio> estudios = daoEstudio.findAll(Estudio.class);

      JsonArrayBuilder estudiosArray = Json.createArrayBuilder();

      for(Estudio estudio: estudios) {
          JsonObject estu = Json.createObjectBuilder()
            .add("id", estudio.get_id())
            .add("estado", estudio.get_estado())
            .add("encuestaId", estudio.get_encuesta().get_id())
            .build();

          estudiosArray.add(estu);
      }

      data = Json.createObjectBuilder().add("estudios", estudiosArray)
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
