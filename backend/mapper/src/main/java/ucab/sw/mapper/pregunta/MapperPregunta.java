package ucab.sw.mapper.pregunta;

import ucab.dsw.accesodatos.DaoOpcion;
import ucab.dsw.accesodatos.DaoPreguntaOpcion;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.PreguntaOpcionDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Opcion;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.PreguntaOpcion;
import ucab.dsw.logica.fabrica.Fabrica;

public class MapperPregunta {

  public static Pregunta MapPreguntaDtoToEntityAdd(PreguntaDto preguntaDto){

    try {

      Pregunta pregunta = Fabrica.crear(Pregunta.class);
      pregunta.set_descripcionPregunta(preguntaDto.getDescripcionPregunta());
      pregunta.set_min(preguntaDto.getMin());
      pregunta.set_max(preguntaDto.getMax());
      pregunta.set_tipoPregunta(preguntaDto.getTipoPregunta());

      return pregunta;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static PreguntaDto MapEntityToPreguntaDto(Pregunta pregunta){

    try {

      PreguntaDto preguntaDto = Fabrica.crear(PreguntaDto.class);
      preguntaDto.setId(pregunta.get_id());
      preguntaDto.setDescripcionPregunta(pregunta.get_descripcionPregunta());
      preguntaDto.setMin(pregunta.get_min());
      preguntaDto.setMax(pregunta.get_max());
      preguntaDto.setTipoPregunta(pregunta.get_tipoPregunta());

      return preguntaDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static PreguntaOpcion MapPreguntaOpcionAdd(Pregunta pregunta, Opcion opcion){

    try {

      PreguntaOpcion preguntaOpcion = Fabrica.crear(PreguntaOpcion.class);
      preguntaOpcion.set_pregunta(pregunta);
      preguntaOpcion.set_opcion(opcion);

      return preguntaOpcion;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

}
