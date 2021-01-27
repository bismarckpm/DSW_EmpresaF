package ucab.sw.mapper.muestra;

import ucab.dsw.entidades.Muestra;
import ucab.dsw.logica.fabrica.Fabrica;

public class MapperMuestra {

  public static Muestra MappMuestraToEntity(Muestra muestra){

    try{

      muestra.set_estado("completo");

      return muestra;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

}
