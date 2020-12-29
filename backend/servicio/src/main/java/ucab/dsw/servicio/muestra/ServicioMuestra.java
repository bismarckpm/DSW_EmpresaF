package ucab.dsw.servicio.muestra;

import ucab.dsw.accesodatos.DaoEncuestado;
import ucab.dsw.accesodatos.DaoMuestra;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.MuestraDto;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.Muestra;
import ucab.dsw.entidades.SolicitudEstudio;

import javax.json.Json;
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

@Path("/muestra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioMuestra {


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
          daoMuestra.insert(muestra);
        }

      }else{

        if(periodo.getYears() >= solicitudEstudio.get_edadInicial()) {
          Muestra muestra = new Muestra();
          muestra.set_encuestado(encuestado);
          muestra.set_solicitudEstudio(solicitudEstudio);
          daoMuestra.insert(muestra);
        }
      }
    }
  }

  @POST
  @Path("/add/{idSolicitudEstudio}")
  public Response addManualMuestra(@PathParam("idSolicitudEstudio") long idSolicitudEstudio, MuestraDto muestraDto){
    JsonObject data;
    try{
      Muestra muestra = new Muestra();
      DaoMuestra daoMuestra = new DaoMuestra();

      DaoEncuestado daoEncuestado = new DaoEncuestado();
      Encuestado encuestado = daoEncuestado.find(muestraDto.getEncuestado().getId(), Encuestado.class);
      muestra.set_encuestado(encuestado);

      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(idSolicitudEstudio, SolicitudEstudio.class);
      muestra.set_solicitudEstudio(solicitudEstudio);

      Muestra resultado = daoMuestra.insert(muestra);

      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("encuestadoAgregado", resultado.get_encuestado().get_id())
        .add("solicitudEstudio", resultado.get_solicitudEstudio().get_id())
        .build();

      System.out.println(data);
      return Response.ok().entity(data).build();

    }catch (Exception ex){

      ex.printStackTrace();
      return Response.ok().entity(null).build();
    }
  }
}
