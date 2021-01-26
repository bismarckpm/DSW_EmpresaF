package ucab.dsw.logica.comando.estudio;

import ucab.dsw.accesodatos.DaoEncuesta;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.EncuestaDto;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.encuesta.MapperEncuesta;
import ucab.sw.mapper.estudio.MapperEstudio;

import javax.json.Json;
import javax.json.JsonObject;
import java.lang.reflect.InvocationTargetException;

public class ComandoGetEstudio  implements ComandoBase {

  private long idEstudio;
  private EstudioDto estudioDto;

  public ComandoGetEstudio(long idEstudio) {
    this.idEstudio = idEstudio;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException {

    try {

      DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
      Estudio estudio = daoEstudio.find(this.idEstudio, Estudio.class);

      EncuestaDto encuestaDto = MapperEncuesta.MapEntityToEncuestaDto(estudio.get_encuesta());

      this.estudioDto = MapperEstudio.MapEntityToEstudioDtoGet(estudio, encuestaDto);


    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    String resultadoEstudio;

    try{

      if(this.estudioDto.getResultado() != null){

        resultadoEstudio = this.estudioDto.getResultado();

      }else{

        resultadoEstudio = "Sin resultados";

      }

      JsonObject data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("id", this.estudioDto.getId())
        .add("nombreEstudio", this.estudioDto.getNombreEstudio())
        .add("estado", this.estudioDto.getEstado())
        .add("resultadoEstudio", resultadoEstudio)
        .add("encuestaId", this.estudioDto.getEncuesta().getId())
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }
}
