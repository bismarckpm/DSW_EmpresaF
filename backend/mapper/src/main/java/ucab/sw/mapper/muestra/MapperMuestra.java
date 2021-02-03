package ucab.sw.mapper.muestra;

import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.Muestra;
import ucab.dsw.entidades.SolicitudEstudio;
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

  public static Muestra MappMuestraToEntityAdd(Encuestado encuestado, SolicitudEstudio solicitudEstudio){

    try{

      Muestra muestraAgregar = Fabrica.crear(Muestra.class);
      muestraAgregar.set_encuestado(encuestado);
      muestraAgregar.set_solicitudEstudio(solicitudEstudio);
      muestraAgregar.set_estado("pendiente");

      return muestraAgregar;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

}
