package ucab.dsw.logica.comando.estudio;


import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.EncuestaDto;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.encuesta.MapperEncuesta;
import ucab.sw.mapper.estudio.MapperEstudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ComandoGetEstudios implements ComandoBase {

  private JsonArrayBuilder estudiosDto = Json.createArrayBuilder();

  public ComandoGetEstudios() {
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException {

    try {

      DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
      List<Estudio> estudios = daoEstudio.findAll(Estudio.class);

      for(Estudio estudio:estudios){

        EncuestaDto encuestaDto = MapperEncuesta.MapEntityToEncuestaDto(estudio.get_encuesta());

        EstudioDto estudioDto = MapperEstudio.MapEntityToEstudioDtoGet(estudio, encuestaDto);

        String resultadoEstudio;

        if(estudioDto.getResultado() != null){

          resultadoEstudio = estudioDto.getResultado();

        }else{

          resultadoEstudio = "Sin resultados";

        }

        JsonObject est = Json.createObjectBuilder()
          .add("id", estudioDto.getId())
          .add("nombreEstudio", estudioDto.getNombreEstudio())
          .add("estado", estudioDto.getEstado())
          .add("resultadoEstudio", resultadoEstudio)
          .add("encuestaId", estudioDto.getEncuesta().getId())
          .build();

        estudiosDto.add(est);
      }

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("estudios", this.estudiosDto)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }
}
