package ucab.dsw.logica.comando.estudio;

import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.sw.mapper.estudio.MapperEstudio;

import javax.json.Json;
import javax.json.JsonObject;
import java.lang.reflect.InvocationTargetException;

public class ComandoAddEstudio implements ComandoBase {

  private EstudioDto estudioDto;
  private long solicitudId;

  public ComandoAddEstudio(long solicitudId, EstudioDto estudioDto) {
    this.estudioDto = estudioDto;
    this.solicitudId = solicitudId;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException {

    try{

      Estudio estudioAgregado = MapperEstudio.MapEstudioDtoToEntityAdd(this.estudioDto, this.solicitudId);

      this.estudioDto = MapperEstudio.MapEntityToEstudioDtoAdd(estudioAgregado);


    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("estudioId", this.estudioDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }
}
