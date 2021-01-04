package ucab.dsw.servicio.muestra;

import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoEncuestado;
import ucab.dsw.accesodatos.DaoMuestra;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.EncuestadoDto;
import ucab.dsw.dtos.MuestraDto;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.Muestra;
import ucab.dsw.entidades.SolicitudEstudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Clase para gestionar la muestra
 *
 */
@Path("/muestra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioMuestra {

  /**
   * Metodo para añadir usuarios encuestados a la muestra de una solicitud de estudio de forma automatica
   * al crearse una solicitud de estudio
   *
   * @param encuestados lista de encuestados
   * @param solicitudEstudio Solicitud de estudio
   *
   */
  public void addMuestra(List<Encuestado> encuestados, SolicitudEstudio solicitudEstudio){


    DaoMuestra daoMuestra = new DaoMuestra();

    for(Encuestado encuestado:encuestados){

      DateFormat fecha = new SimpleDateFormat(("dd-MM-yyyy"));
      String fechaConvertido = fecha.format(encuestado.get_fechaNacimiento());

      DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate fechaParse = LocalDate.parse(fechaConvertido, fmt);

      Period periodo = Period.between(fechaParse, LocalDate.now());

      if(solicitudEstudio.get_edadfinal() != null){

        if(periodo.getYears() >= solicitudEstudio.get_edadInicial() && periodo.getYears() <= solicitudEstudio.get_edadfinal()) {
          Muestra muestra = new Muestra();
          muestra.set_encuestado(encuestado);
          muestra.set_solicitudEstudio(solicitudEstudio);
          muestra.set_estado("pendiente");
          daoMuestra.insert(muestra);
        }

      }else{

        if(periodo.getYears() >= solicitudEstudio.get_edadInicial()) {
          Muestra muestra = new Muestra();
          muestra.set_encuestado(encuestado);
          muestra.set_solicitudEstudio(solicitudEstudio);
          muestra.set_estado("pendiente");
          daoMuestra.insert(muestra);
        }
      }
    }
  }

  /**
   * Metodo para agregar usuarios a la muestra de una soicitud de estudio
   * Accedido mediante /muestra/add/{idSolicitudEstudio} con el
   * metodo POST
   *
   * @param idSolicitudEstudio Id de la solicitud de estudio
   * @param muestraDto DTO de la muestra
   * @return JSON success: {code, estado, solicitudEstudio}; error: {estado,
   * code}
   */
  @POST
  @Path("/add/{idSolicitudEstudio}")
  public Response addManualMuestra(@PathParam("idSolicitudEstudio") long idSolicitudEstudio, MuestraDto muestraDto){
    JsonObject data;
    try{
      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(idSolicitudEstudio, SolicitudEstudio.class);

      DaoMuestra daoMuestra = new DaoMuestra();

      for(EncuestadoDto encuestados:muestraDto.getEncuestados()) {
        Muestra muestra = new Muestra();
        DaoEncuestado daoEncuestado = new DaoEncuestado();

        Encuestado encuestado = daoEncuestado.find(encuestados.getId(), Encuestado.class);
        muestra.set_encuestado(encuestado);

        muestra.set_solicitudEstudio(solicitudEstudio);

        daoMuestra.insert(muestra);
      }

      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("solicitudEstudio", idSolicitudEstudio)
        .build();

      System.out.println(data);
      return Response.ok().entity(data).build();

    }catch (Exception ex){

      data = Json.createObjectBuilder()
        .add("code", 400)
        .add("estado", "error")
        .build();
      return Response.ok().entity(data).build();
    }
  }


  /**
   * Metodo para obtener la muestra dada una solicitud de estudio
   * Accedido mediante /muestra/getmuestra/{solicitudId} con el
   * metodo GET
   *
   * @param solicitudId Id de la solicitud de estudio
   *
   * @return JSON success: {code, estado, encuestados}; error: {estado,
   * code}
   */
  @GET
  @Path("/getmuestra/{solicitudId}")
  public Response getMuestra(@PathParam("solicitudId") long solicitudId){

    JsonObject data;

    try {
      DaoMuestra daoMuestra = new DaoMuestra();

      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(solicitudId, SolicitudEstudio.class);

      List<Encuestado> encuestadosMuestra = daoMuestra.getEncuestadosMuestraBySolicitud(solicitudEstudio);

      JsonArrayBuilder encuestadosArray = Json.createArrayBuilder();

      for (Encuestado encuestado : encuestadosMuestra) {
        JsonObject encu = Json.createObjectBuilder()
          .add("encuestadoId", encuestado.get_id())
          .add("encuestadoNombre", encuestado.get_primerNombre())
          .add("encuestadoApellido", encuestado.get_primerApellido())
          .build();

        encuestadosArray.add(encu);
      }

      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("encuestados", encuestadosArray).build();

    }
    catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("code", 400)
        .add("estado", "error").build();

      System.out.println(data);
      return Response.ok().entity(data).build();
    }

    System.out.println(data);
    return Response.ok().entity(data).build();
  }
}
