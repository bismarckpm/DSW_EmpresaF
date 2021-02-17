package ucab.sw.mapper.opcion;

import ucab.dsw.dtos.OpcionDto;
import ucab.dsw.entidades.Opcion;


public class MapperOpcion {

  public static OpcionDto MapOpcionToDto(Opcion opcion){

    try{

      OpcionDto opcionDto = new OpcionDto();
      opcionDto.setId(opcion.get_id());
      opcionDto.setDescripcion(opcion.get_descripcion());

      return opcionDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Opcion MapOpcionDtoToEntity(OpcionDto opcionDto){

    try{

      Opcion opcion = new Opcion();
      opcion.set_descripcion(opcionDto.getDescripcion());

      return opcion;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

}
