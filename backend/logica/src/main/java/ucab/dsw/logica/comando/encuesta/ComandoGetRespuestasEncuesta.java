package ucab.dsw.logica.comando.encuesta;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.OpcionDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.RangoExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.encuesta.MapperEncuesta;
import ucab.sw.mapper.opcion.MapperOpcion;
import ucab.sw.mapper.respuesta.MapperRespuesta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ComandoGetRespuestasEncuesta implements ComandoBase {

  private long idEncuesta;
  private JsonArrayBuilder respuestasArray = Json.createArrayBuilder();

  public ComandoGetRespuestasEncuesta(long idEncuesta) {
    this.idEncuesta = idEncuesta;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion {

    try {

      DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);

      DaoEncuesta daoEncuesta = Fabrica.crear(DaoEncuesta.class);
      Encuesta encuesta = daoEncuesta.find(this.idEncuesta, Encuesta.class);

      List<PreguntaEncuesta> preguntaEncuestas = daoPreguntaEncuesta.getPreguntasEncuestaByEncuestaId(encuesta);

      JsonArrayBuilder opcionArray = Json.createArrayBuilder();

      for(PreguntaEncuesta preguntaEncuesta:preguntaEncuestas){

        DaoPregunta daoPregunta = Fabrica.crear(DaoPregunta.class);
        Pregunta pregunta = daoPregunta.find(preguntaEncuesta.get_pregunta().get_id(), Pregunta.class);
        PreguntaDto preguntaDto = MapperEncuesta.MapEntityToPreguntaInEncuestaDto(pregunta);

        if(preguntaEncuesta.get_pregunta().get_tipoPregunta().equals("simple") || preguntaEncuesta.get_pregunta().get_tipoPregunta().equals("multiple")){

          List<Opcion> opciones;
          opciones = preguntaEncuesta.get_pregunta().getOpciones();

          for(Opcion opcion:opciones){

            DaoRespuestaOpcion daoRespuestaOpcion = Fabrica.crear(DaoRespuestaOpcion.class);
            Integer respuestaCont = daoRespuestaOpcion.contRespuesta(opcion);

            OpcionDto opcionDto = MapperOpcion.MapOpcionToDto(opcion);

            JsonObject option = Json.createObjectBuilder()
              .add("opcion",opcionDto.getDescripcion())
              .add("opcionId", opcionDto.getId())
              .add("conteo",respuestaCont)
              .build();

            opcionArray.add(option);

          }

          JsonObject answer = Json.createObjectBuilder()
            .add("pregunta", preguntaDto.getDescripcionPregunta())
            .add("tipoPregunta", preguntaDto.getTipoPregunta())
            .add("opciones", opcionArray)
            .build();

          respuestasArray.add(answer);

        }else{

          DaoRespuesta daoRespuesta = new DaoRespuesta();
          List<Respuesta> answers = daoRespuesta.findAll(Respuesta.class);

          for(Respuesta respuesta: answers){

            if(respuesta.get_preguntaEncuesta().get_id() == preguntaEncuesta.get_id()){

              RespuestaDto respuestaDto = MapperRespuesta.MapRespuestaToDto(respuesta);

              JsonObject answer = Json.createObjectBuilder()
                .add("pregunta", preguntaDto.getDescripcionPregunta())
                .add("tipoPregunta", preguntaDto.getTipoPregunta())
                .add("respuesta",respuestaDto.getDescripcion())
                .add("rango", respuestaDto.getRango()).build();

              respuestasArray.add(answer);

            }

          }

        }

      }

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("respuestas", respuestasArray)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
