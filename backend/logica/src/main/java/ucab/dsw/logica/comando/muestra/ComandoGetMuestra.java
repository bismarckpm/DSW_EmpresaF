package ucab.dsw.logica.comando.muestra;

import ucab.dsw.accesodatos.DaoMuestra;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.EncuestadoDto;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.usuario.MapperUsuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ComandoGetMuestra implements ComandoBase {

  private long idSolicitud;
  private JsonArrayBuilder encuestadosArray = Json.createArrayBuilder();

  public ComandoGetMuestra(long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

  public void execute() throws Exception {

    try {

      DaoMuestra daoMuestra = Fabrica.crear(DaoMuestra.class);

      DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(this.idSolicitud, SolicitudEstudio.class);

      List<Encuestado> encuestadosMuestra = daoMuestra.getEncuestadosMuestraBySolicitud(solicitudEstudio);

      for (Encuestado encuestado : encuestadosMuestra) {

        DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
        Usuario usuario = daoUsuario.find(encuestado.get_usuario().get_id(), Usuario.class);

        EncuestadoDto encuestadoDto = MapperUsuario.MapEntityToEncuestadoDto(encuestado);

        JsonObject encu = Json.createObjectBuilder()
          .add("encuestadoId", encuestadoDto.getId())
          .add("encuestadoNombre", encuestadoDto.getPrimerNombre())
          .add("encuestadoApellido", encuestadoDto.getPrimerApellido())
          .add("usuarioId", usuario.get_id())
          .build();

        encuestadosArray.add(encu);

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
        .add("encuestados", this.encuestadosArray).build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
