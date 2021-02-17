package ucab.sw.mapper.respuesta;

import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.Respuesta;

public class MapperRespuesta {

  public static RespuestaDto MapRespuestaToDto(Respuesta respuesta){

    try {

      RespuestaDto respuestaDto = new RespuestaDto();
      respuestaDto.setDescripcion(respuesta.get_descripcion());
      respuestaDto.setRango(respuesta.get_rango());

      return respuestaDto;

    }catch (Exception ex){
      throw ex;
    }

  }
}
