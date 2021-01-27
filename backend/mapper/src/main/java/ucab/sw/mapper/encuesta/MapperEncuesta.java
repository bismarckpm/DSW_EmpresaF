package ucab.sw.mapper.encuesta;

import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.logica.fabrica.Fabrica;

import java.util.Date;

public class MapperEncuesta {

  public static EncuestaDto MapEntityToEncuestaDto(Encuesta encuesta){

    try{

      EncuestaDto encuestaDto = Fabrica.crear(EncuestaDto.class);
      encuestaDto.setId(encuesta.get_id());
      encuestaDto.setNombreEncuesta(encuesta.get_nombreEncuesta());


      return encuestaDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static PreguntaEncuesta MapPreguntaEncuestaDtoToEntityAdd(Pregunta pregunta, Encuesta encuesta){

    try{

      PreguntaEncuesta preguntaEncuesta = Fabrica.crear(PreguntaEncuesta.class);
      preguntaEncuesta.set_encuesta(encuesta);
      preguntaEncuesta.set_pregunta(pregunta);

      return preguntaEncuesta;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Respuesta MapRespuestaEncuestaDtoToEntityAdd(ArrayRespuestaDto respuestaDto, Date fecha, Integer rango, Encuestado encuestado, PreguntaEncuesta preguntaEncuesta){

    try{

      Respuesta respuesta = Fabrica.crear(Respuesta.class);
      respuesta.set_fecha(fecha);
      respuesta.set_descripcion(respuestaDto.getDescripcion());
      respuesta.set_encuestado(encuestado);
      respuesta.set_rango(rango);
      respuesta.set_preguntaEncuesta(preguntaEncuesta);

      return respuesta;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static RespuestaOpcion MapRespuestaOpcionToEntity(Opcion opcion, Respuesta respuestaAgregada){

    try{

      RespuestaOpcion respuestaOpcion = Fabrica.crear(RespuestaOpcion.class);

      respuestaOpcion.set_opcion(opcion);
      respuestaOpcion.set_respuesta(respuestaAgregada);

      return respuestaOpcion;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static PreguntaEncuestaDto MapEntityToPreguntaEncuestaDto(PreguntaEncuesta preguntaEncuesta){

    try{

      PreguntaEncuestaDto preguntaEncuestaDto = Fabrica.crear(PreguntaEncuestaDto.class);
      preguntaEncuestaDto.setId(preguntaEncuesta.get_id());

      return preguntaEncuestaDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }


  public static PreguntaDto MapEntityToPreguntaInEncuestaDto(Pregunta pregunta){

    try{

      PreguntaDto preguntaDto = Fabrica.crear(PreguntaDto.class);
      preguntaDto.setId(pregunta.get_id());
      preguntaDto.setDescripcionPregunta(pregunta.get_descripcionPregunta());
      preguntaDto.setTipoPregunta(pregunta.get_tipoPregunta());
      preguntaDto.setMin(pregunta.get_min());
      preguntaDto.setMax(pregunta.get_max());

      return preguntaDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }
}
