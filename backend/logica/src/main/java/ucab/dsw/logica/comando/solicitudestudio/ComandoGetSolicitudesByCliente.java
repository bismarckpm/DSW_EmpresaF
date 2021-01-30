package ucab.dsw.logica.comando.solicitudestudio;

import ucab.dsw.accesodatos.DaoEncuesta;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.RangoExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.solicitudestudio.MapperSolicitud;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.naming.NamingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ComandoGetSolicitudesByCliente implements ComandoBase {

  private JsonArrayBuilder solicitudesArray = Json.createArrayBuilder();
  private long idCliente;

  public ComandoGetSolicitudesByCliente(long idCliente) {
    this.idCliente = idCliente;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion, NamingException {

    long estudioId;
    long encuestaId;
    String resultadoEstudio;

    try {

      DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
      List<SolicitudEstudio> solicitudes = daoSolicitudEstudio.findAll(SolicitudEstudio.class);

      for(SolicitudEstudio solicitud:solicitudes){

        if(solicitud.get_edadfinal() == null) {

          solicitud.set_edadfinal(0);

        }

        if(solicitud.get_cliente().get_id() == this.idCliente){

          if(solicitud.get_estudio()!=null && solicitud.get_estudio().get_resultado()!=null) {

            DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
            Estudio estudio = daoEstudio.find(solicitud.get_estudio().get_id(), Estudio.class);

            DaoEncuesta daoEncuesta = new DaoEncuesta();
            Encuesta encuesta = daoEncuesta.find(estudio.get_encuesta().get_id(), Encuesta.class);

            estudioId = estudio.get_id();
            encuestaId = encuesta.get_id();
            resultadoEstudio = estudio.get_resultado();

          }else {

            estudioId = 0;
            encuestaId = 0;
            resultadoEstudio = "Sin resultados hasta el momento";

          }

          SolicitudEstudioDto solicitudEstudioDto = MapperSolicitud.MapEntityToSolicitudDto(solicitud);

          JsonObject solis = Json.createObjectBuilder().
            add("id", solicitudEstudioDto.getId()).
            add("estudioId", estudioId).
            add("resultadoEstudio", resultadoEstudio).
            add("encuestaId", encuestaId).
            add("edadInicial", solicitudEstudioDto.getEdadInicial()).
            add("edadFinal", solicitudEstudioDto.getEdadfinal()).
            add("genero", solicitudEstudioDto.getGenero()).
            add("estado", solicitudEstudioDto.getEstado()).
            add("cliente", solicitud.get_cliente().get_nombreUsuario()).
            add("subcategoria", solicitud.get_subcategoria().get_nombreSubcategoria()).
            add("nivelSocioeconomico", solicitud.get_nivelSocioeconomico().getTipo()).
            add("parroquia", solicitud.get_parroquia().get_nombreParroquia()).build();

          solicitudesArray.add(solis);

        }

      }

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("solicitudes", this.solicitudesArray).build();

      return data;

    }catch (Exception ex){
      throw  ex;
    }

  }

}
