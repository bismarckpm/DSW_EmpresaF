package ucab.dsw.logica.comando.solicitudestudio;

import ucab.dsw.accesodatos.DaoEncuestado;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.muestra.ServicioMuestra;
import ucab.sw.mapper.solicitudestudio.MapperSolicitud;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoAddSolicitud implements ComandoBase {

  private SolicitudEstudioDto solicitudEstudioDto;

  public ComandoAddSolicitud(SolicitudEstudioDto solicitudEstudioDto) {
    this.solicitudEstudioDto = solicitudEstudioDto;
  }

  public void execute() throws Exception {

    try {

      SolicitudEstudio solicitudEstudio = MapperSolicitud.MapSolicitudDtoToEntityAdd(this.solicitudEstudioDto);

      DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
      SolicitudEstudio resultado = daoSolicitudEstudio.insert(solicitudEstudio);

      inicializarMuestra(solicitudEstudio);

      this.solicitudEstudioDto = MapperSolicitud.MapEntityToSolicitudDto(resultado);

    }catch (LimiteExcepcion ex){

      throw new ProblemaExcepcion(ex.getMessage(), "Los limites posiblemente estan invertidos");

    }
    catch (SolicitudPendienteExcepcion ex){

      throw new ProblemaExcepcion(ex.getMessage(), "El cliente ya posee una solicitud de estudio con las mismas caracteristicas en espera");

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("solicitud", this.solicitudEstudioDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return  data;

    }catch (Exception ex){
      throw ex;
    }
  }

  /**
   * Metodo para asignar usuarios a la muestra a una solicitud de estudio dependiendo
   * de sus caracteristicas demográficas
   *
   *
   * @param solicitudEstudio solicitud de estudio
   *
   */
  private static void inicializarMuestra(SolicitudEstudio solicitudEstudio) throws Exception {

    DaoEncuestado dao = Fabrica.crear(DaoEncuestado.class);
    List<Encuestado> usuariosEncuestados;

    if(solicitudEstudio.get_genero().equals("ambos")) {

      usuariosEncuestados = dao.getUsersMuestra(solicitudEstudio);

    }else {

      usuariosEncuestados = dao.getUsersMuestraByGenero(solicitudEstudio);

    }

    ServicioMuestra servicioMuestra = Fabrica.crear(ServicioMuestra.class);
    servicioMuestra.addMuestra(usuariosEncuestados, solicitudEstudio);

  }

}
