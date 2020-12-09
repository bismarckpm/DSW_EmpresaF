package ucab.dsw.servicio.solicitudEstudio;

import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
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
      solicitudEstudio.set_edadInicial(solicitudEstudioDto.getEdadInicial());
      solicitudEstudio.set_edadfinal(solicitudEstudioDto.getEdadfinal());
      solicitudEstudio.set_genero(solicitudEstudioDto.getGenero());

      Usuario usuarioCliente = new Usuario(solicitudEstudioDto.getCliente().getId());
      solicitudEstudio.set_cliente(usuarioCliente);

      Parroquia parroquia = new Parroquia(solicitudEstudioDto.getParroquia().getId());
      solicitudEstudio.set_parroquia(parroquia);

      Marca marca = new Marca(solicitudEstudioDto.getMarca().getId());
      solicitudEstudio.set_marca(marca);

      NivelSocioeconomico nivelSocioeconomico = new NivelSocioeconomico(solicitudEstudioDto.getNivelSocioeconomico().getId());
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
        Usuario usuario = new Usuario(82);
        solicitudEstudio.set_administrador(usuario);
        solicitudEstudioAgregada = daoSolicitudEstudio.insert(solicitudEstudio);

      }

      data = Json.createObjectBuilder().add("solicitud", solicitudEstudioAgregada.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();

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

  @PUT
  @Path("/asignarsolicitud/{idSolicitud}")
  public Response asignarEstudioASolicitud(@PathParam("idSolicitud") long id, SolicitudEstudioDto solicitudEstudioDto){
    JsonObject data;

    try{
      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

      Estudio estudio = new Estudio(solicitudEstudioDto.getEstudio().getId());
      solicitudEstudio.set_estudio(estudio);
      solicitudEstudio.set_estado("procesado");

      int random = (int) (Math.random()*(82-81+1)+81);
      Usuario analista = new Usuario(random);
      solicitudEstudio.set_analista(analista);
      SolicitudEstudio resultado = daoSolicitudEstudio.update(solicitudEstudio);

      data = Json.createObjectBuilder().
        add("id", resultado.get_id())
        .add("estado", "success")
        .add("code", 200).build();

      System.out.println(data);
      return Response.ok().entity(data).build();

    }catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400).build();

      System.out.println(data);
      return Response.ok().entity(data).build();
    }
  }

  /* @GET
  @Path("/getsolicitud/{id}")
  public Response getSolicitudById(@PathParam("id") long id){
    JsonObject data;
    try{
      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

      data = Json.createObjectBuilder().
        add("id", solicitudEstudio.get_id()).
        add("edadInicial", solicitudEstudio.get_edadfinal()).
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

      System.out.println(data);
      return Response.ok().entity(data).build();

    }
    catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return Response.ok().entity(data).build();
    }

  }*/

}
