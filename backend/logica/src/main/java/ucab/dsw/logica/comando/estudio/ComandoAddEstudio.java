package ucab.dsw.logica.comando.estudio;

import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.estudio.MapperEstudio;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;

public class ComandoAddEstudio implements ComandoBase {

  private EstudioDto estudioDto;
  private long solicitudId;

  public ComandoAddEstudio(long solicitudId, EstudioDto estudioDto) {
    this.estudioDto = estudioDto;
    this.solicitudId = solicitudId;
  }

  public void execute() throws Exception {

    try{

      Estudio estudioAgregado = MapperEstudio.MapEstudioDtoToEntityAdd(this.estudioDto, this.solicitudId);

      this.estudioDto = MapperEstudio.MapEntityToEstudioDtoAdd(estudioAgregado);


    }catch (javax.persistence.PersistenceException ex){

      throw new ProblemaExcepcion("Este estudio y/o encuesta ya se encuentra registrado", ex.getMessage());

    }catch (LimiteExcepcion ex){

      throw new ProblemaExcepcion(ex.getMessage(), "Los limites posiblemente estan invertidos");

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
