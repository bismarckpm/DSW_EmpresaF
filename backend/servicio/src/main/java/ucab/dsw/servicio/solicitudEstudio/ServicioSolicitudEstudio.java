package ucab.dsw.servicio.solicitudEstudio;

import ucab.dsw.accesodatos.DaoNivelSocioeconomico;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
      solicitudEstudio.set_edadfinal(solicitudEstudioDto.getEdadInicial());
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
      SolicitudEstudio solicitudExistente = asignarSolicitud(solicitudEstudio);

      if( solicitudExistente != null){

        solicitudEstudio.set_analista(solicitudExistente.get_analista());
        solicitudEstudioAgregada = daoSolicitudEstudio.insert(solicitudEstudio);

      }else{
        Usuario usuario = new Usuario(81);
        solicitudEstudio.set_analista(usuario);
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

  public SolicitudEstudio asignarSolicitud(SolicitudEstudio solicitudEstudio){
    DaoSolicitudEstudio dao = new DaoSolicitudEstudio();
    SolicitudEstudio solicitudEstudioPrevia = dao.getSolicitudesByCaracteristicas(solicitudEstudio);

    if(solicitudEstudioPrevia != null){
      return solicitudEstudioPrevia;
    }else{
      return null;
    }

  }

}
