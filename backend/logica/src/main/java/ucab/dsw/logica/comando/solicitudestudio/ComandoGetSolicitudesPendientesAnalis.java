package ucab.dsw.logica.comando.solicitudestudio;

import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;
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

public class ComandoGetSolicitudesPendientesAnalis implements ComandoBase {

  long idUsuarioAnalista;
  private JsonArrayBuilder solicitudesDtos = Json.createArrayBuilder();

  public ComandoGetSolicitudesPendientesAnalis(long idUsuarioAnalista) {
    this.idUsuarioAnalista = idUsuarioAnalista;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion, NamingException {

    try{

      DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
      List<SolicitudEstudio> solicitudesPendientes = daoSolicitudEstudio.findAll(SolicitudEstudio.class);

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      Usuario usuario = daoUsuario.find(this.idUsuarioAnalista, Usuario.class);

      for(SolicitudEstudio solicitudes:solicitudesPendientes) {

        if (solicitudes.get_analista() != null && solicitudes.get_analista().get_id() == usuario.get_id() && solicitudes.get_estado().equals("solicitado")) {

          SolicitudEstudioDto solicitudEstudioDto = MapperSolicitud.MapEntityToSolicitudDto(solicitudes);

          JsonObject solicitudesEstudios = Json.createObjectBuilder().
            add("id", solicitudEstudioDto.getId()).
            add("edadInicial", solicitudEstudioDto.getEdadInicial()).
            add("edadFinal", solicitudEstudioDto.getEdadfinal()).
            add("genero", solicitudEstudioDto.getGenero()).
            add("estado", solicitudEstudioDto.getEstado()).
            add("cliente", solicitudes.get_cliente().get_nombreUsuario()).
            add("subcategoria", solicitudes.get_subcategoria().get_nombreSubcategoria()).
            add("nivelSocioeconomico", solicitudes.get_nivelSocioeconomico().getTipo()).
            add("parroquia", solicitudes.get_parroquia().get_nombreParroquia()).build();

          solicitudesDtos.add(solicitudesEstudios);

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
        .add("id", this.idUsuarioAnalista)
        .add("solicitudes", this.solicitudesDtos).build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
