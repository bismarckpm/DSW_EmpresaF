package ucab.dsw.servicio.estudio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.OpcionDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
   * Metodo para agregar un estudio y asignarlo a una solicitud Accedido mediante estudio/add/{solicitudEstudioId} con el
   * metodo POST
   *
   * @param estudioDto DTO del estudio
   * @return JSON success: {estudioId, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/add/{solicitudEstudioId}")
  public Response addEstudio (@PathParam("solicitudEstudioId") long solicitudId, EstudioDto estudioDto){
    JsonObject data;

    try {

      Estudio estudio = new Estudio();
      estudio.set_nombreEstudio(estudioDto.getNombreEstudio());
      estudio.set_estado("procesado");

      Date fecha = new Date();
      estudio.set_fechaInicio(fecha);

      Encuesta encuesta = new Encuesta();
      encuesta.set_nombreEncuesta(estudioDto.getEncuesta().getNombreEncuesta());

      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(solicitudId, SolicitudEstudio.class);

      DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
      Subcategoria subcategoria = daoSubcategoria.find(solicitudEstudio.get_subcategoria().get_id(), Subcategoria.class);
      encuesta.set_subcategoria(subcategoria);

      estudio.set_encuesta(encuesta);

      DaoEstudio daoEstudio = new DaoEstudio();
      Estudio estudioAgregado = daoEstudio.insert(estudio);

      solicitudEstudio.set_estudio(estudioAgregado);
      solicitudEstudio.set_estado("procesado");

      Usuario usuario = new Usuario(2);
      solicitudEstudio.set_analista(usuario);

      daoSolicitudEstudio.update(solicitudEstudio);

      DaoPregunta daoPregunta = new DaoPregunta();

      for(PreguntaDto pregunta:estudioDto.getPreguntas()){
        if(pregunta.getId() <= 0 ){

          Pregunta pregun = new Pregunta();
          pregun.set_descripcionPregunta(pregunta.getDescripcionPregunta());

          if(pregunta.getMin() > pregunta.getMax()){
            throw new LimiteExcepcion("El limite superior no puede ser menor al limite inferior");
          }else {
            pregun.set_min(pregunta.getMin());
            pregun.set_max(pregunta.getMax());
          }

          pregun.set_tipoPregunta(pregunta.getTipoPregunta());

          Pregunta preguntaAgregada = daoPregunta.insert(pregun);

          DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

          PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();
          preguntaEncuesta.set_pregunta(preguntaAgregada);
          preguntaEncuesta.set_encuesta(encuesta);

          daoPreguntaEncuesta.insert(preguntaEncuesta);

          if(pregun.get_tipoPregunta().equals("desarrollo")){
            DaoOpcion daoOpcion = new DaoOpcion();
            Integer id = 8;
            Opcion opcion = daoOpcion.find(id.longValue(), Opcion.class);

            DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();
            PreguntaOpcion preguntaOpcion = new PreguntaOpcion();
            preguntaOpcion.set_pregunta(preguntaAgregada);
            preguntaOpcion.set_opcion(opcion);

            daoPreguntaOpcion.insert(preguntaOpcion);
          }

          pregun.set_id(preguntaAgregada.get_id());

          List<OpcionDto> opcionesDtos = pregunta.getOpciones();
          List<Opcion> opciones = new ArrayList<>();

          if (opcionesDtos != null) {
            for (OpcionDto opcion : opcionesDtos) {
              DaoOpcion dao = new DaoOpcion();
              Opcion op = new Opcion();

              op.set_descripcion(opcion.getDescripcion());
              op = dao.insert(op);

              opciones.add(op);
              opcion.setId(op.get_id());
            }

            DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();
            for (Opcion opcion : opciones) {
              PreguntaOpcion preguntaOpcion = new PreguntaOpcion();
              preguntaOpcion.set_opcion(opcion);
              preguntaOpcion.set_pregunta(preguntaAgregada);

              daoPreguntaOpcion.insert(preguntaOpcion);
            }

            pregun.setOpciones(opciones);
          }
        }else{
          DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
          PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();
          preguntaEncuesta.set_encuesta(encuesta);

          Pregunta question = daoPregunta.find(pregunta.getId(), Pregunta.class);

          preguntaEncuesta.set_pregunta(question);
          daoPreguntaEncuesta.insert(preguntaEncuesta);
        }

      }


      data = Json.createObjectBuilder().add("estudioId", estudioAgregado.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();

    }catch (javax.persistence.PersistenceException ex){
      data = Json.createObjectBuilder().add("mensaje", "Este estudio y/o encuesta ya se encuentra registrado")
        .add("estado", "success")
        .add("code", 400)
        .build();

      return  Response.status(400).entity(data).build();
    }
    catch (Exception ex){
      data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
        .add("estado", "error")
        .add("code", 400)
        .build();
      return  Response.status(400).entity(data).build();
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
    String resultadoEstudio;

    try{
      Estudio estudio = daoEstudio.find(id, Estudio.class);
      if(estudio.get_resultado() != null){
        resultadoEstudio = estudio.get_resultado();
      }else{
        resultadoEstudio = "Sin resultados";
      }

      data = Json.createObjectBuilder()
        .add("id", estudio.get_id())
        .add("nombreEstudio", estudio.get_nombreEstudio())
        .add("estado", estudio.get_estado())
        .add("resultadoEstudio", resultadoEstudio)
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
    String resultadoEstudio;
    try {

      DaoEstudio daoEstudio = new DaoEstudio();
      List<Estudio> estudios = daoEstudio.findAll(Estudio.class);

      JsonArrayBuilder estudiosArray = Json.createArrayBuilder();

      for(Estudio estudio: estudios) {
          if(estudio.get_resultado() != null){
            resultadoEstudio = estudio.get_resultado();
          }else{
            resultadoEstudio = "Sin resultados";
          }

          JsonObject estu = Json.createObjectBuilder()
            .add("id", estudio.get_id())
            .add("nombreEstudio", estudio.get_nombreEstudio())
            .add("estado", estudio.get_estado())
            .add("resultadoEstudio", resultadoEstudio)
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
