package ucab.dsw.logica.comando.muestra;

import ucab.dsw.accesodatos.DaoEncuestado;
import ucab.dsw.accesodatos.DaoMuestra;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.EncuestadoDto;
import ucab.dsw.dtos.MuestraDto;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.Muestra;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.muestra.MapperMuestra;

import javax.json.Json;
import javax.json.JsonObject;

public class ComandoAddMuestraManual implements ComandoBase {

  private long idSolicitud;
  private MuestraDto muestraDto;

  public ComandoAddMuestraManual(long idSolicitud, MuestraDto muestraDto) {
    this.idSolicitud = idSolicitud;
    this.muestraDto = muestraDto;
  }

  public void execute() throws Exception {

    try {

      DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(this.idSolicitud, SolicitudEstudio.class);

      DaoMuestra daoMuestra = Fabrica.crear(DaoMuestra.class);

      for(EncuestadoDto encuestados:this.muestraDto.getEncuestados()) {

        DaoEncuestado daoEncuestado = Fabrica.crear(DaoEncuestado.class);
        Encuestado encuestado = daoEncuestado.find(encuestados.getId(), Encuestado.class);

        Muestra muestra = MapperMuestra.MappMuestraToEntityAdd(encuestado, solicitudEstudio);
        daoMuestra.insert(muestra);

      }

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject  data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("solicitudEstudio", this.idSolicitud)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
