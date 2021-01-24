package ucab.dsw.logica.comando.solicitudestudio;

import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.categoria.MapperCategoria;
import ucab.sw.mapper.solicitudestudio.MapperSolicitud;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ComandoGetSolicitudes implements ComandoBase {

  private JsonArrayBuilder solicitudesDto = Json.createArrayBuilder();

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion {

    try{

      DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
      List<SolicitudEstudio> solicitudEstudios = daoSolicitudEstudio.findAll(SolicitudEstudio.class);

      for(SolicitudEstudio solicitudEstudio:solicitudEstudios){

        SolicitudEstudioDto solicitudEstudioDto = MapperSolicitud.MapEntityToSolicitudDto(solicitudEstudio);

        JsonObject solicitud = Json.createObjectBuilder().
          add("id", solicitudEstudioDto.getId()).
          add("edadInicial", solicitudEstudioDto.getEdadInicial()).
          add("edadFinal", solicitudEstudioDto.getEdadfinal()).
          add("genero", solicitudEstudioDto.getGenero()).
          add("estado", solicitudEstudioDto.getEstado()).
          add("cliente", solicitudEstudio.get_cliente().get_nombreUsuario()).
          add("subcategoria", solicitudEstudio.get_subcategoria().get_nombreSubcategoria()).
          add("nivelSocioeconomico", solicitudEstudio.get_nivelSocioeconomico().getTipo()).
          add("parroquia", solicitudEstudio.get_parroquia().get_nombreParroquia()).build();

        solicitudesDto.add(solicitud);
      }

    }catch (Exception ex){
      throw  ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("solicitudes", this.solicitudesDto).build();

      return data;

    }catch (Exception ex){
      throw  ex;
    }

  }

}
