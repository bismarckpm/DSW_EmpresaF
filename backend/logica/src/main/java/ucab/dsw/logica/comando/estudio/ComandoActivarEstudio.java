package ucab.dsw.logica.comando.estudio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.RangoExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.estudio.MapperEstudio;
import ucab.sw.mapper.solicitudestudio.MapperSolicitud;

import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.NamingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ComandoActivarEstudio implements ComandoBase {

  private long solicitudId;
  private SolicitudEstudioDto solicitudEstudioDto;

  public ComandoActivarEstudio(long solicitudId) {
    this.solicitudId = solicitudId;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion, NamingException {

    try{

      DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
      SolicitudEstudio solicitud = daoSolicitudEstudio.find(this.solicitudId, SolicitudEstudio.class);

      List<SolicitudEstudio> solicitudesEstudio = daoSolicitudEstudio.getSolicitudesByCaracteristicas(solicitud);
      DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);

      for(SolicitudEstudio solicitudes:solicitudesEstudio){

        if(solicitudes.get_analista() == solicitud.get_analista() &&
          (solicitudes.get_estado().equals("procesado") || solicitudes.get_estado().equals("ejecutando") || solicitudes.get_estado().equals("culminado"))){

          Estudio estudio = daoEstudio.find(solicitudes.get_estudio().get_id(), Estudio.class);

          estudio.set_estado("procesado");
          daoEstudio.update(estudio);

          solicitud.set_estudio(estudio);
          solicitud.set_estado("procesado");

          break;

        }

      }

      SolicitudEstudio resultado = daoSolicitudEstudio.update(solicitud);

      this.solicitudEstudioDto = MapperSolicitud.MapEntityToSolicitudDto(resultado);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("idSolicitud", this.solicitudId)
        .add("estadoSolicitud", this.solicitudEstudioDto.getEstado()).build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
