package ucab.sw.mapper.respuesta;

import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.logica.fabrica.Fabrica;

public class MapperRespuesta {

  public static RespuestaDto MapRespuestaToDto(Respuesta respuesta){

    try {

      RespuestaDto respuestaDto = Fabrica.crear(RespuestaDto.class);
      respuestaDto.setDescripcion(respuesta.get_descripcion());
      respuestaDto.setRango(respuesta.get_rango());

      return respuestaDto;

    }catch (Exception ex){
      throw ex;
    }

  }
}
