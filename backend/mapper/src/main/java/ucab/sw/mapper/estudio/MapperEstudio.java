package ucab.sw.mapper.estudio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;


import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public class MapperEstudio {

  public static Estudio MapEstudioDtoToEntityAdd(EstudioDto estudioDto, long solicitudId) throws InvocationTargetException, PruebaExcepcion, LimiteExcepcion, InstantiationException, IllegalAccessException {

    try {

      Estudio estudio = new Estudio();
      estudio.set_nombreEstudio(estudioDto.getNombreEstudio());
      estudio.set_estado("procesado");

      Date fecha = new Date();
      estudio.set_fechaInicio(fecha);

      Encuesta encuesta = new Encuesta();
      encuesta.set_nombreEncuesta(estudioDto.getEncuesta().getNombreEncuesta());

      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(solicitudId, SolicitudEstudio.class);

      DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
      Subcategoria subcategoria = daoSubcategoria.find(solicitudEstudio.get_subcategoria().get_id(), Subcategoria.class);
      encuesta.set_subcategoria(subcategoria);

      estudio.set_encuesta(encuesta);

      DaoEstudio daoEstudio = new DaoEstudio();
      Estudio estudioAgregado = daoEstudio.insert(estudio);

      solicitudEstudio.set_estudio(estudioAgregado);
      solicitudEstudio.set_estado("procesado");

      DaoUsuario daoUsuario = new DaoUsuario();
      Usuario usuario = daoUsuario.find((long) 2, Usuario.class);
      solicitudEstudio.set_analista(usuario);

      daoSolicitudEstudio.update(solicitudEstudio);

      DaoPregunta daoPregunta = new DaoPregunta();

      for(PreguntaDto pregunta:estudioDto.getPreguntas()){

        if(pregunta.getId() <= 0 ){

          Pregunta pregun = new Pregunta();
          pregun.set_descripcionPregunta(pregunta.getDescripcionPregunta());

          if(pregunta.getMin() > pregunta.getMax()){

            throw new LimiteExcepcion("El limite superior no puede ser menor al limite inferior");

          }else {

            pregun.set_min(pregunta.getMin());
            pregun.set_max(pregunta.getMax());

          }

          pregun.set_tipoPregunta(pregunta.getTipoPregunta());

          Pregunta preguntaAgregada = daoPregunta.insert(pregun);

          DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

          PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();
          preguntaEncuesta.set_pregunta(preguntaAgregada);
          preguntaEncuesta.set_encuesta(encuesta);

          daoPreguntaEncuesta.insert(preguntaEncuesta);

          if(pregun.get_tipoPregunta().equals("desarrollo")){

            DaoOpcion daoOpcion = new DaoOpcion();
            Integer id = 8;
            Opcion opcion = daoOpcion.find(id.longValue(), Opcion.class);

            DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();
            PreguntaOpcion preguntaOpcion = new PreguntaOpcion();
            preguntaOpcion.set_pregunta(preguntaAgregada);
            preguntaOpcion.set_opcion(opcion);

            daoPreguntaOpcion.insert(preguntaOpcion);

          }

          List<OpcionDto> opcionesDtos = pregunta.getOpciones();

          if (opcionesDtos != null && ((pregun.get_tipoPregunta().equals("multiple")) || pregun.get_tipoPregunta().equals("simple"))) {

            for (OpcionDto opcion : opcionesDtos) {

              DaoOpcion dao = new DaoOpcion();
              Opcion op = new Opcion();

              op.set_descripcion(opcion.getDescripcion());
              Opcion opcioAgregada = dao.insert(op);

              DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();
              PreguntaOpcion preguntaOpcion = new PreguntaOpcion();

              preguntaOpcion.set_opcion(opcioAgregada);
              preguntaOpcion.set_pregunta(preguntaAgregada);

              daoPreguntaOpcion.insert(preguntaOpcion);
              System.out.println("Inserté en pregunta opcion");

            }

          }

        }else{

          DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
          PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();
          preguntaEncuesta.set_encuesta(encuesta);

          Pregunta question = daoPregunta.find(pregunta.getId(), Pregunta.class);

          preguntaEncuesta.set_pregunta(question);
          daoPreguntaEncuesta.insert(preguntaEncuesta);

        }

      }

      return estudioAgregado;

    }catch (Exception ex){
      throw ex;
    }

  }

  public static EstudioDto MapEntityToEstudioDtoAdd(Estudio estudio){

    try{

      EstudioDto estudioDto = new EstudioDto();
      estudioDto.setId(estudio.get_id());
      estudioDto.setNombreEstudio(estudio.get_nombreEstudio());

      return estudioDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }


  public static EstudioDto MapEntityToEstudioDtoGet(Estudio estudio, EncuestaDto encuestaDto){

    try{

      EstudioDto estudioDto = new EstudioDto();
      estudioDto.setId(estudio.get_id());
      estudioDto.setNombreEstudio(estudio.get_nombreEstudio());
      estudioDto.setEstado(estudio.get_estado());
      estudioDto.setResultado(estudio.get_resultado());
      estudioDto.setEncuesta(encuestaDto);

      return estudioDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }
}
