package ucab.dsw.logica.comando.muestra;

import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoEncuestado;
import ucab.dsw.accesodatos.DaoMuestra;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.EncuestadoDto;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.usuario.MapperUsuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ComandoGetUsuarioAgregable implements ComandoBase {

  private long idSolicitud;
  private JsonArrayBuilder encuestadosArray = Json.createArrayBuilder();

  public ComandoGetUsuarioAgregable(long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

  public void execute() throws Exception {

    try {

      DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(this.idSolicitud, SolicitudEstudio.class);

      DaoEncuestado daoEncuestado = Fabrica.crear(DaoEncuestado.class);
      List<Encuestado> encuestados = daoEncuestado.findAll(Encuestado.class);

      for(Encuestado encuestado:encuestados){

        DaoMuestra daoMuestra = Fabrica.crear(DaoMuestra.class);
        Encuestado encuestadoAgregado = daoMuestra.getEncuestadoAgregable(encuestado, solicitudEstudio);

        if(encuestadoAgregado ==  null){

          EncuestadoDto encuestadoDto = MapperUsuario.MapEntityToEncuestadoDto(encuestado);

          JsonObject encuest = Json.createObjectBuilder()
            .add("encuestadoId", encuestadoDto.getId())
            .add("primerNombre", encuestadoDto.getPrimerNombre())
            .add("primerApellido", encuestadoDto.getPrimerApellido())
            .add("genero", encuestadoDto.getGenero())
            .add("estadoCivil", encuestadoDto.getEstadoCivil())
            .build();

          encuestadosArray.add(encuest);

        }

      }

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("encuestados", this.encuestadosArray).build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
