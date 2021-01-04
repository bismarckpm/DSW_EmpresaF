package ucab.dsw.servicio.estudio;

import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Clase para gestionar los estudios
 *
 */
@Path( "/estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstudio extends AplicacionBase {

  /**
   * Metodo para agregar un estudio Accedido mediante estudio/add/ con el
   * metodo POST
   *
   * @param estudioDto DTO del estudio
   * @return JSON success: {estudioId, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/add")
  public Response addEstudio (EstudioDto estudioDto){
    JsonObject data;

    try {

      Estudio estudio = new Estudio();
      estudio.set_nombreEstudio(estudioDto.getNombreEstudio());
      estudio.set_estado("procesado");

      Date fecha = new Date();
      estudio.set_fechaInicio(fecha);

      Encuesta encuesta = new Encuesta(estudioDto.getEncuesta().getId());
      estudio.set_encuesta(encuesta);

      DaoEstudio daoEstudio = new DaoEstudio();
      Estudio estudioAgregado = daoEstudio.insert(estudio);

      data = Json.createObjectBuilder().add("estudioId", estudioAgregado.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();

    }catch (javax.persistence.PersistenceException ex){
      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400)
        .add("mensaje", "Ya existe un estudio con este nombre, intenta de nuevo")
        .build();

      System.out.println(data);
      return  Response.ok().entity(data).build();
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

  /**
   * Metodo para obtener un estudio Accedido mediante estudio/getestudio/{estudioId} con el
   * metodo GET
   *
   * @param id Id del estudio
   * @return JSON success: {id, nombreEstudio, encuestaId, estado, code}; error: {estado,
   * code}
   */
  @GET
  @Path("/getestudio/{estudioId}")
  public Response getEstudioById(@PathParam("estudioId") long id){

    DaoEstudio daoEstudio = new DaoEstudio();
    JsonObject data;

    try{
      Estudio estudio = daoEstudio.find(id, Estudio.class);

      data = Json.createObjectBuilder()
        .add("id", estudio.get_id())
        .add("nombreEstudio", estudio.get_nombreEstudio())
        .add("estado", estudio.get_estado())
        .add("encuestaId", estudio.get_encuesta().get_id())
        .build();

      return Response.ok().entity(data).build();

    }catch (Exception ex){

      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400)
        .build();
    }

    return Response.ok().entity(data).build();
  }


  /**
   * Metodo para obtener todos los estudios Accedido mediante estudio/getall con el
   * metodo GET
   *
   *
   * @return JSON success: {estudios, estado, code}; error: {estado,
   * code}
   */
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
            .add("nombreEstudio", estudio.get_nombreEstudio())
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
