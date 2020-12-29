package ucab.dsw.servicio.encuesta;

import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.Opcion;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.RespuestaOpcion;
import ucab.dsw.excepciones.RangoExcepcion;

/**
 * Clase para gestionar las respuestas de una pregunta de una encuesta
 *
 */
@Path("/encuestas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioEncuestaRespuesta {


   /* @POST
    @Path("/{idEncuesta}/preguntas/{idPregunta}/respuesta")
    public Response addRespuesta(
            @PathParam("idEncuesta") long _idEncuesta,
            @PathParam("idPregunta") long _idPregunta,
            RespuestaDto respuestaDto) {

        JsonObject data;
        try {

            Respuesta respuesta = new Respuesta();
            DaoRespuesta daoRespuestas = new DaoRespuesta();

            DaoEncuesta daoEncuesta = new DaoEncuesta();
            Encuesta encuesta = daoEncuesta.find(_idEncuesta, Encuesta.class);

            DaoPregunta daoPregunta = new DaoPregunta();
            Pregunta pregunta = daoPregunta.find(_idPregunta, Pregunta.class);

            PreguntaEncuesta preguntaEncuesta = null;

            for (PreguntaEncuesta _preguntasEncuesta : encuesta.get_preguntasEncuestas()) {
                if (_preguntasEncuesta.get_pregunta().get_id() == _idPregunta) {
                    preguntaEncuesta = _preguntasEncuesta;
                }
            }

            DaoEncuestado daoEncuestado = new DaoEncuestado();
            Encuestado encuestado = daoEncuestado.find(respuestaDto.getEncuestado().getId(), Encuestado.class);

            Date fecha = new Date();

            respuesta.set_fecha(fecha);
            respuesta.set_descripcion(respuestaDto.getDescripcion());
            respuesta.set_rango(respuestaDto.getRango());
            respuesta.set_encuestado(encuestado);
            respuesta.set_preguntaEncuesta(preguntaEncuesta);

            respuesta = daoRespuestas.insert(respuesta);

            List<OpcionDto> opciones = respuestaDto.getOpciones();
            DaoRespuestaOpcion daoRespuestaOpcion = new DaoRespuestaOpcion();

            for (OpcionDto opcionDto : opciones) {
                RespuestaOpcion respuestaOpcion = new RespuestaOpcion();
                respuestaOpcion.set_opcion(new Opcion(opcionDto.getId()));
                respuestaOpcion.set_respuesta(respuesta);

                daoRespuestaOpcion.insert(respuestaOpcion);
            }

            data = Json.createObjectBuilder()
                    .add("data", respuesta.get_id())
                    .add("estado", "success")
                    .add("code", 200)
                    .build();

        } catch (javax.persistence.PersistenceException ex) {
            String mensaje = "Estas opciones ya se encuentran añadidas";
            data = Json.createObjectBuilder()
                    .add("mensaje", mensaje)
                    .add("estado", "error")
                    .add("code", 400)
                    .build();
            return Response.status(400).entity(data).build();
        } catch (Exception ex) {
            data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();
            return Response.status(400).entity(data).build();
        }

        return Response.status(200).entity(data).build();
    }*/

    @POST
    @Path("/respuesta/{idEncuesta}")
    public Response addRespuesta(@PathParam("idEncuesta") long idEncuesta, BaseRespuestaDto baseDto){

      try{
        DaoEncuesta daoEncuesta = new DaoEncuesta();
        Encuesta encuesta = daoEncuesta.find(idEncuesta, Encuesta.class);

        for(ArrayRespuestaDto respuesta:baseDto.getRespuestas()){
          DaoPregunta daoPregunta = new DaoPregunta();
          Pregunta pregunta = daoPregunta.find(respuesta.getPregunta().getId(), Pregunta.class);

          DaoEncuestado daoEncuestado = new DaoEncuestado();
          Encuestado encuestado = daoEncuestado.find(respuesta.getEncuestado().getId(), Encuestado.class);

          Respuesta res = new Respuesta();
          Date fecha = new Date();

          res.set_fecha(fecha);
          res.set_descripcion(respuesta.getDescripcion());
          res.set_encuestado(encuestado);

          System.out.println(pregunta.get_min() );
          System.out.println(pregunta.get_max() );
          System.out.println(respuesta.getRango());
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
        ex.printStackTrace();


        return Response.ok().entity(null).build();
      }
    }

    @GET
    @Path("/respuesta/{idEncuesta}")
    public Response getRespuestaByEncuesta(@PathParam("idEncuesta") long idEncuesta){
      List<Respuesta> respuestas;
      List<PreguntaEncuesta> preguntaEncuestas;
      String pregunta = null;
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
          pregunta = daoPregunta.find(preguntaEncuesta.get_pregunta().get_id(), Pregunta.class).get_descripcionPregunta();

          if(preguntaEncuesta.get_pregunta().get_tipoPregunta().equals("simple") || preguntaEncuesta.get_pregunta().get_tipoPregunta().equals("multiple")){

            List<Opcion> opciones;
            opciones = preguntaEncuesta.get_pregunta().getOpciones();
            for(Opcion opcion:opciones){
              DaoRespuestaOpcion daoRespuestaOpcion = new DaoRespuestaOpcion();
              Integer respuestaCont = daoRespuestaOpcion.contRespuesta(opcion);

              JsonObject option = Json.createObjectBuilder()
                .add("opcion", opcion.get_descripcion())
                .add("opcionId", opcion.get_id())
                .add("conteo", respuestaCont)
                .build();

              opcionArray.add(option);
            }

            JsonObject answer = Json.createObjectBuilder()
              .add("pregunta", pregunta)
              .add("opciones", opcionArray)
              .build();

            respuestasArray.add(answer);

          }else{
            respuestas = preguntaEncuesta.get_respuestas();
            for (Respuesta respuesta:respuestas){
              JsonObject answer = Json.createObjectBuilder()
                .add("pregunta", pregunta)
                .add("respuesta",respuesta.get_descripcion())
                .add("rango", respuesta.get_rango()).build();

              respuestasArray.add(answer);
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
