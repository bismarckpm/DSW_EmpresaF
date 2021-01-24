package ucab.dsw.logica.exepcionhandler;

import javax.json.Json;
import javax.json.JsonObject;

public class ManejadorExcepcion {

  public JsonObject getMensajeError(String mensaje_soporte, String mensaje, String estado, Integer code){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("mensaje_soporte", mensaje_soporte)
        .add("mensaje", mensaje)
        .add("estado", estado)
        .add("code", code)
        .build();

      return data;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }
}
