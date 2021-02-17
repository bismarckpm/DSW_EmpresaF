package ucab.dsw.logica.comando.pregunta;

import ucab.dsw.accesodatos.DaoEncuesta;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.pregunta.MapperPregunta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ComandoGetPreguntasSugeridas implements ComandoBase {

  private long idSolicitud;
  private JsonArrayBuilder preguntasArray = Json.createArrayBuilder();

  public ComandoGetPreguntasSugeridas(long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

  public void execute() throws Exception {

    try {

      DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
      SolicitudEstudio solicitud = daoSolicitudEstudio.find(this.idSolicitud, SolicitudEstudio.class);

      DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
      Subcategoria subcategoria = daoSubcategoria.find(solicitud.get_subcategoria().get_id(), Subcategoria.class);

      DaoEncuesta daoEncuesta = Fabrica.crear(DaoEncuesta.class);
      List<Encuesta> encuestas = daoEncuesta.getEncuestasBySubcategoria(subcategoria);

      ArrayList<String> arrayListPregunta = Fabrica.crear(ArrayList.class);

      for (Encuesta encuest: encuestas){

        for(Pregunta pregunta: encuest.getPreguntas()) {

          if(!arrayListPregunta.contains(pregunta.get_descripcionPregunta())){

            PreguntaDto preguntaDto = MapperPregunta.MapEntityToPreguntaDto(pregunta);

            JsonObject question = Json.createObjectBuilder()
              .add("preguntaId", preguntaDto.getId())
              .add("subcategoria", subcategoria.get_nombreSubcategoria())
              .add("descripcionPregunta", preguntaDto.getDescripcionPregunta())
              .add("tipoPregunta", preguntaDto.getTipoPregunta()).build();

            preguntasArray.add(question);
            arrayListPregunta.add(preguntaDto.getDescripcionPregunta());

          }

        }

      }

    }catch (Exception ex){
      ex.printStackTrace();
    }

  }

  public JsonObject getResultado(){
    try {

      JsonObject data = Json.createObjectBuilder()
        .add("preguntas", this.preguntasArray)
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
       throw ex;
    }

  }

}
