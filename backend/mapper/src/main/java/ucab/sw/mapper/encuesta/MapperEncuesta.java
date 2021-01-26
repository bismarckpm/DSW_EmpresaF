package ucab.sw.mapper.encuesta;

import ucab.dsw.dtos.EncuestaDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.logica.fabrica.Fabrica;

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
