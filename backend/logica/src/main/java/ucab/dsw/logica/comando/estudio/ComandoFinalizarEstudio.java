package ucab.dsw.logica.comando.estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.RangoExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.estudio.MapperEstudio;

import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.NamingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public class ComandoFinalizarEstudio implements ComandoBase {

  private long estudioId;
  private EstudioDto estudioDto;

  public ComandoFinalizarEstudio(long estudioId, EstudioDto estudioDto) {
    this.estudioId = estudioId;
    this.estudioDto = estudioDto;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion, NamingException {

    try {

      DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
      Estudio estudio = daoEstudio.find(this.estudioId, Estudio.class);

      estudio.set_estado("culminado");
      estudio.set_resultado(estudioDto.getResultado());

      Date fecha = new Date();
      estudio.set_fechaFin(fecha);

      List<SolicitudEstudio> solicitudEstudios = estudio.get_solicitudesEstudio();

      for(SolicitudEstudio solicitud:solicitudEstudios){

        solicitud.set_estado("culminado");
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        daoSolicitudEstudio.update(solicitud);

      }

      Estudio resultado = daoEstudio.update(estudio);

      this.estudioDto = MapperEstudio.MapEntityToEstudioDtoAdd(resultado);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("estudioCulminado", this.estudioDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
