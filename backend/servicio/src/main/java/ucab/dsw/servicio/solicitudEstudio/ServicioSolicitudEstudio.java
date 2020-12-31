package ucab.dsw.servicio.solicitudEstudio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.servicio.AplicacionBase;
import ucab.dsw.servicio.muestra.ServicioMuestra;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/solicitud" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSolicitudEstudio extends AplicacionBase {


  @POST
  @Path("/add")
  public Response addSolicitud(SolicitudEstudioDto solicitudEstudioDto){

    JsonObject data;
    try {

      SolicitudEstudio solicitudEstudio = new SolicitudEstudio();
      solicitudEstudio.set_estado("solicitado");

      if(solicitudEstudioDto.getEdadInicial() > solicitudEstudioDto.getEdadfinal()){
        throw new LimiteExcepcion("El limite superior no puede ser menor al limite inferior");
      }else {
        solicitudEstudio.set_edadInicial(solicitudEstudioDto.getEdadInicial());
        solicitudEstudio.set_edadfinal(solicitudEstudioDto.getEdadfinal());
      }

      solicitudEstudio.set_genero(solicitudEstudioDto.getGenero());

      DaoUsuario daoUsuario = new DaoUsuario();
      Usuario cliente = daoUsuario.find(solicitudEstudioDto.getCliente().getId(), Usuario.class);
      solicitudEstudio.set_cliente(cliente);

      DaoParroquia daoParroquia = new DaoParroquia();
      Parroquia parroquia = daoParroquia.find(solicitudEstudioDto.getParroquia().getId(), Parroquia.class);
      solicitudEstudio.set_parroquia(parroquia);

      DaoMarca daoMarca = new DaoMarca();
      Marca marca = daoMarca.find(solicitudEstudioDto.getMarca().getId(), Marca.class);
      solicitudEstudio.set_marca(marca);

      DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
      NivelSocioeconomico nivelSocioeconomico = daoNivelSocioeconomico.find(solicitudEstudioDto.getNivelSocioeconomico().getId(), NivelSocioeconomico.class);
      solicitudEstudio.set_nivelSocioeconomico(nivelSocioeconomico);

      SolicitudEstudio solicitudEstudioAgregada;

      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      List<SolicitudEstudio> solicitudesExistentes = asignarSolicitud(solicitudEstudio);


      if( !solicitudesExistentes.isEmpty()){

        for(SolicitudEstudio soli:solicitudesExistentes){
          solicitudEstudio.set_analista(soli.get_analista());
          break;
        }

        solicitudEstudioAgregada = daoSolicitudEstudio.insert(solicitudEstudio);

      }else{
        DaoUsuario dao = new DaoUsuario();
        Integer id = 4;
        Usuario usuario = dao.find(id.longValue(), Usuario.class);
        solicitudEstudio.set_administrador(usuario);
        solicitudEstudioAgregada = daoSolicitudEstudio.insert(solicitudEstudio);
      }

     inicializarMuestra(solicitudEstudio);

      data = Json.createObjectBuilder().add("solicitud", solicitudEstudioAgregada.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();

    }
    catch (LimiteExcepcion ex){
      data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return  Response.ok().entity(data).build();
    }
    catch (Exception ex){
      data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return  Response.ok().entity(data).build();
    }

    System.out.println(data);
    return  Response.ok().entity(data).build();
  }

  private List<SolicitudEstudio> asignarSolicitud(SolicitudEstudio solicitudEstudio){
    DaoSolicitudEstudio dao = new DaoSolicitudEstudio();
    List<SolicitudEstudio> solicitudEstudioPrevia = dao.getSolicitudesByCaracteristicas(solicitudEstudio);

    if(solicitudEstudioPrevia != null){

      return solicitudEstudioPrevia;

    }else{

      return null;

    }

  }

  private void inicializarMuestra(SolicitudEstudio solicitudEstudio){
    DaoEncuestado dao = new DaoEncuestado();
    List<Encuestado> usuariosEncuestados = dao.getUsersMuestra(solicitudEstudio);
    ServicioMuestra servicioMuestra = new ServicioMuestra();
    servicioMuestra.addMuestra(usuariosEncuestados, solicitudEstudio);
  }

  @GET
  @Path("/getall")
  public Response getSolicitudes() {
    JsonObject data;

    try {
      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      List<SolicitudEstudio> solicitudesEstudio = daoSolicitudEstudio.findAll(SolicitudEstudio.class);

      JsonArrayBuilder solicitudesArray = Json.createArrayBuilder();

      for (SolicitudEstudio solicitudEstudio : solicitudesEstudio) {
        if(solicitudEstudio.get_edadfinal() == null) {
          solicitudEstudio.set_edadfinal(0);
        }

        JsonObject sol = Json.createObjectBuilder().
          add("id", solicitudEstudio.get_id()).
          add("edadInicial", solicitudEstudio.get_edadInicial()).
          add("edadFinal", solicitudEstudio.get_edadfinal()).
          add("genero", solicitudEstudio.get_genero()).
          add("estado", solicitudEstudio.get_estado()).
          add("cliente", solicitudEstudio.get_cliente().get_nombreUsuario()).
          add("marca", solicitudEstudio.get_marca().get_nombreMarca()).
          add("tipoMarca", solicitudEstudio.get_marca().get_tipoMarca()).
          add("capacidadMarca", solicitudEstudio.get_marca().get_capacidad()).
          add("unidadMarca", solicitudEstudio.get_marca().get_unidad()).
          add("unidadSubcategoria", solicitudEstudio.get_marca().get_subcategoria().get_nombreSubcategoria()).
          add("nivelSocioeconomico", solicitudEstudio.get_nivelSocioeconomico().getTipo()).
          add("parroquia", solicitudEstudio.get_parroquia().get_nombreParroquia()).build();

        solicitudesArray.add(sol);
      }
      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("solicitudes", solicitudesArray).build();


    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }

    System.out.println(data);
    return Response.ok().entity(data).build();
  }
}
