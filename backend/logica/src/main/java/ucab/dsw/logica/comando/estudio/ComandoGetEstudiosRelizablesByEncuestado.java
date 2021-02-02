package ucab.dsw.logica.comando.estudio;

import ucab.dsw.accesodatos.DaoEncuesta;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoMuestra;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.estudio.MapperEstudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ComandoGetEstudiosRelizablesByEncuestado implements ComandoBase {

  private long usuarioId;
  private JsonArrayBuilder estudioRealizableArray = Json.createArrayBuilder();

  public ComandoGetEstudiosRelizablesByEncuestado(long usuarioId) {
    this.usuarioId = usuarioId;
  }

  public void execute() throws Exception {

    try {

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      Usuario usuario = daoUsuario.find(this.usuarioId, Usuario.class);

      Encuestado encuestado = usuario.get_encuestado();

      DaoMuestra daoMuestra = Fabrica.crear(DaoMuestra.class);
      List<SolicitudEstudio> solicitudes = daoMuestra.getEstudiosRealizablesByEncuestado(encuestado);

      for (SolicitudEstudio solicitud: solicitudes) {

        if (solicitud.get_estado().equals("procesado") || solicitud.get_estado().equals("ejecutando")) {

          DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
          List<Estudio> estudios = daoEstudio.findAll(Estudio.class);

          for(Estudio estudio:estudios){

            if(solicitud.get_estudio().get_id() == estudio.get_id()){

              DaoEncuesta daoEncuesta = Fabrica.crear(DaoEncuesta.class);
              Encuesta encuesta = daoEncuesta.find(estudio.get_encuesta().get_id(), Encuesta.class);

              EstudioDto estudioDto = MapperEstudio.MapEntityToEstudioDtoAdd(estudio);

              JsonObject estu = Json.createObjectBuilder()
                .add("estudioId", estudioDto.getId())
                .add("nombreEstudio", estudioDto.getNombreEstudio())
                .add("encuestaId", encuesta.get_id())
                .build();

              estudioRealizableArray.add(estu);

            }

          }

        }

      }

    }catch (Exception ex){

    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "sucess")
        .add("estudios", this.estudioRealizableArray).build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
