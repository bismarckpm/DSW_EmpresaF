package ucab.dsw.logica.comando.pregunta;

import ucab.dsw.accesodatos.DaoOpcion;
import ucab.dsw.accesodatos.DaoPregunta;
import ucab.dsw.accesodatos.DaoPreguntaOpcion;
import ucab.dsw.dtos.OpcionDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.entidades.Opcion;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.PreguntaOpcion;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.opcion.MapperOpcion;
import ucab.sw.mapper.pregunta.MapperPregunta;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ComandoAddPregunta implements ComandoBase {

  private PreguntaDto preguntaDto;

  public ComandoAddPregunta(PreguntaDto preguntaDto) {
    this.preguntaDto = preguntaDto;
  }

  public void execute() throws Exception {

    try {

      Pregunta pregunta;

      if(preguntaDto.getMin() > preguntaDto.getMax()){

        throw new LimiteExcepcion("El limite superior no puede ser menor al limite inferior");

      }else {

         pregunta = MapperPregunta.MapPreguntaDtoToEntityAdd(preguntaDto);

      }

      DaoPregunta daoPregunta = Fabrica.crear(DaoPregunta.class);
      Pregunta preguntaAgregada = daoPregunta.insert(pregunta);

      if(preguntaDto.getTipoPregunta().equals("desarrollo")){

        DaoOpcion daoOpcion = Fabrica.crear(DaoOpcion.class);
        Integer id = 8;
        Opcion opcion = daoOpcion.find(id.longValue(), Opcion.class);

        PreguntaOpcion preguntaOpcion = MapperPregunta.MapPreguntaOpcionAdd(preguntaAgregada, opcion);
        DaoPreguntaOpcion daoPreguntaOpcion = Fabrica.crear(DaoPreguntaOpcion.class);

        daoPreguntaOpcion.insert(preguntaOpcion);

      }

      this.preguntaDto.setId(preguntaAgregada.get_id());

      List<OpcionDto> opcionesDtos = this.preguntaDto.getOpciones();
      List<Opcion> opciones = Fabrica.crear(ArrayList.class);

      if (opcionesDtos != null) {

        for (OpcionDto opcionDto : opcionesDtos) {

          DaoOpcion dao = Fabrica.crear(DaoOpcion.class);

          Opcion opcion = MapperOpcion.MapOpcionDtoToEntity(opcionDto);
          Opcion op = dao.insert(opcion);

          opciones.add(op);
          opcionDto.setId(op.get_id());

        }

        DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();
        for (Opcion opcion : opciones) {

          PreguntaOpcion preguntaOpcion = MapperPregunta.MapPreguntaOpcionAdd(preguntaAgregada, opcion);

          daoPreguntaOpcion.insert(preguntaOpcion);

        }

        preguntaDto.setOpciones(opcionesDtos);

      }

      this.preguntaDto = MapperPregunta.MapEntityToPreguntaDto(preguntaAgregada);

    }catch (Exception ex){
      throw ex;
    }

  }

  public  JsonObject getResultado(){
    try{

      JsonObject data = Json.createObjectBuilder()
        .add("data", this.preguntaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
