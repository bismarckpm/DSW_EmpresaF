package ucab.dsw.servicio.encuesta;

import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.RangoExcepcion;

/**
 * Clase para gestionar las respuestas de una pregunta de una encuesta
 *
 */
@Path("/encuestas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioEncuestaRespuesta {

  /**
   * Metodo para agregar las respuestas de una encuesta
   * Accedido mediante /encuestas/respuestas/{idEncuesta} con el metodo POST
   *
   * @param idEncuesta id de la encuesta
   * @param baseDto DTO base para recibir un arreglo de respuestas
   *
   * @return JSON success: {estado, mensaje, code}; error: {mensaje, estado,
   * code}
   *
   */
    @POST
    @Path("/respuesta/{idEncuesta}")
    public Response addRespuesta(@PathParam("idEncuesta") long idEncuesta, BaseRespuestaDto baseDto){

      try{
        DaoEncuesta daoEncuesta = new DaoEncuesta();
        Encuesta encuesta = daoEncuesta.find(idEncuesta, Encuesta.class);

        Encuestado encuestado = null;

        DaoMuestra daoMuestra = new DaoMuestra();

        for(ArrayRespuestaDto respuesta:baseDto.getRespuestas()){
          DaoPregunta daoPregunta = new DaoPregunta();
          Pregunta pregunta = daoPregunta.find(respuesta.getPregunta().getId(), Pregunta.class);

          DaoEncuestado daoEncuestado = new DaoEncuestado();
          encuestado = daoEncuestado.find(respuesta.getEncuestado().getId(), Encuestado.class);

          Respuesta res = new Respuesta();
          Date fecha = new Date();

          res.set_fecha(fecha);
          res.set_descripcion(respuesta.getDescripcion());
          res.set_encuestado(encuestado);

          if( (respuesta.getRango() < pregunta.get_min() ) || (respuesta.getRango() > pregunta.get_max())){
            throw new RangoExcepcion("Rango seleccionado fuera de los límites de maximo y mínimo");
          }else {
            res.set_rango(respuesta.getRango());
          }

          DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
          List<PreguntaEncuesta> preguntaEncuestas = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);

          for (PreguntaEncuesta preguntaEncuesta : preguntaEncuestas) {
            if (preguntaEncuesta.get_encuesta().get_id() == encuesta.get_id() && preguntaEncuesta.get_pregunta().get_id() == pregunta.get_id()) {
              res.set_preguntaEncuesta(preguntaEncuesta);
            }
          }

          DaoRespuesta daoRespuesta = new DaoRespuesta();
          daoRespuesta.insert(res);


          if(respuesta.getOpciones()!=null) {

            for (OpcionDto opcionDto : respuesta.getOpciones()) {
              RespuestaOpcion respuestaOpcion = new RespuestaOpcion();
              DaoOpcion daoOpcion = new DaoOpcion();

              Opcion opcion = daoOpcion.find(opcionDto.getId(), Opcion.class);
              respuestaOpcion.set_opcion(opcion);

              DaoRespuestaOpcion daoRespuestaOpcion = new DaoRespuestaOpcion();
              respuestaOpcion.set_opcion(opcion);
              respuestaOpcion.set_respuesta(res);

              daoRespuestaOpcion.insert(respuestaOpcion);
            }
          }
        }

        Integer flag = 0;
        SolicitudEstudio solicitud = null;

        for(SolicitudEstudio solicitudEstudio: encuesta.get_estudio().get_solicitudesEstudio()){
          List<Muestra> muestras = daoMuestra.findAll(Muestra.class);
          for (Muestra muestra:muestras) {
            if (solicitudEstudio.get_id() == muestra.get_solicitudEstudio().get_id() && encuestado.get_id() == muestra.get_encuestado().get_id()){
              muestra.set_estado("completo");
              daoMuestra.update(muestra);
              solicitud = solicitudEstudio;
            }
            if(solicitudEstudio.get_id() == muestra.get_solicitudEstudio().get_id()){
              if(!muestra.get_estado().equals("completo")){
                flag = flag + 1;
              }
            }
          }
        }

        if(flag == 0){
          DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
          solicitud.set_estado("culminado");

          DaoEstudio daoEstudio = new DaoEstudio();
          Estudio estudio = daoEstudio.find(solicitud.get_estudio().get_id(), Estudio.class);

          estudio.set_estado("culminado");

          Date fecha = new Date();
          estudio.set_fechaFin(fecha);

          daoSolicitudEstudio.update(solicitud);
          daoEstudio.update(estudio);
        }

        JsonObject data = Json.createObjectBuilder()
          .add("estado", "success")
          .add("code", 200).build();

        System.out.println(data);
        return Response.ok().entity(data).build();

      }
      catch (RangoExcepcion ex){
        JsonObject data = Json.createObjectBuilder()
          .add("estado", "error")
          .add("mensaje", ex.getMessage())
          .add("code", 400).build();

        System.out.println(data);
        return Response.ok().entity(data).build();
      }
      catch (Exception ex){
        JsonObject data = Json.createObjectBuilder()
          .add("estado", "error")
          .add("mensaje", ex.getMessage())
          .add("code", 400).build();

        System.out.println(data);

        return Response.ok().entity(data).build();
      }
    }

  /**
   * Metodo para obtener las respuestas de una encuesta
   * Accedido mediante /encuestas/respuesta/{idEncuesta} con el metodo GET
   *
   * @param idEncuesta id de la encuesta
   *
   *
   * @return JSON success: {code, estado, respuestas}; error: {mensaje, estado,
   * code}
   *
   */
    @GET
    @Path("/respuesta/{idEncuesta}")
    public Response getRespuestaByEncuesta(@PathParam("idEncuesta") long idEncuesta){
      List<PreguntaEncuesta> preguntaEncuestas;
      Pregunta pregunta;
      JsonObject data;
      JsonArrayBuilder respuestasArray = Json.createArrayBuilder();

      try{

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

        DaoEncuesta daoEncuesta = new DaoEncuesta();
        Encuesta encuesta = daoEncuesta.find(idEncuesta, Encuesta.class);

        preguntaEncuestas = daoPreguntaEncuesta.getPreguntasEncuestaByEncuestaId(encuesta);

        JsonArrayBuilder opcionArray = Json.createArrayBuilder();

        for(PreguntaEncuesta preguntaEncuesta:preguntaEncuestas){

          DaoPregunta daoPregunta = new DaoPregunta();
          pregunta = daoPregunta.find(preguntaEncuesta.get_pregunta().get_id(), Pregunta.class);

          if(preguntaEncuesta.get_pregunta().get_tipoPregunta().equals("simple") || preguntaEncuesta.get_pregunta().get_tipoPregunta().equals("multiple")){

            List<Opcion> opciones;
            opciones = preguntaEncuesta.get_pregunta().getOpciones();
            for(Opcion opcion:opciones){
              DaoRespuestaOpcion daoRespuestaOpcion = new DaoRespuestaOpcion();
              Integer respuestaCont = daoRespuestaOpcion.contRespuesta(opcion);

              JsonObject option = Json.createObjectBuilder()
                .add("opcion",opcion.get_descripcion())
                .add("opcionId", opcion.get_id())
                .add("conteo",respuestaCont)
                .build();

              opcionArray.add(option);
            }

            JsonObject answer = Json.createObjectBuilder()
              .add("pregunta", pregunta.get_descripcionPregunta())
              .add("tipoPregunta", pregunta.get_tipoPregunta())
              .add("opciones", opcionArray)
              .build();

            respuestasArray.add(answer);

          }else{
            DaoRespuesta daoRespuesta = new DaoRespuesta();
            List<Respuesta> ress = daoRespuesta.findAll(Respuesta.class);
            for(Respuesta respuesta: ress){
              if(respuesta.get_preguntaEncuesta().get_id() == preguntaEncuesta.get_id()){
                JsonObject answer = Json.createObjectBuilder()
                  .add("pregunta", pregunta.get_descripcionPregunta())
                  .add("tipoPregunta", pregunta.get_tipoPregunta())
                  .add("respuesta",respuesta.get_descripcion())
                  .add("rango", respuesta.get_rango()).build();

                respuestasArray.add(answer);
              }
            }
          }
        }

        data = Json.createObjectBuilder()
          .add("code", 200)
          .add("estado", "success")
          .add("respuestas", respuestasArray)
          .build();

        System.out.println(data);
        return Response.ok().entity(data).build();

      }catch (Exception ex){

        data = Json.createObjectBuilder()
          .add("code", 400)
          .add("estado", "error")
          .build();

        return Response.ok().entity(data).build();
      }
    }
}
