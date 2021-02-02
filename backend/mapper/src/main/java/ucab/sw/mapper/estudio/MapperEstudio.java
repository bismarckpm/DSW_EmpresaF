package ucab.sw.mapper.estudio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapperEstudio {

  public static Estudio MapEstudioDtoToEntityAdd(EstudioDto estudioDto, long solicitudId) throws InvocationTargetException, PruebaExcepcion, LimiteExcepcion, InstantiationException, IllegalAccessException {

    try {

      Estudio estudio = Fabrica.crear(Estudio.class);
      estudio.set_nombreEstudio(estudioDto.getNombreEstudio());
      estudio.set_estado("procesado");

      Date fecha = Fabrica.crear(Date.class);
      estudio.set_fechaInicio(fecha);

      Encuesta encuesta = Fabrica.crear(Encuesta.class);
      encuesta.set_nombreEncuesta(estudioDto.getEncuesta().getNombreEncuesta());

      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(solicitudId, SolicitudEstudio.class);

      DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
      Subcategoria subcategoria = daoSubcategoria.find(solicitudEstudio.get_subcategoria().get_id(), Subcategoria.class);
      encuesta.set_subcategoria(subcategoria);

      estudio.set_encuesta(encuesta);

      DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
      Estudio estudioAgregado = daoEstudio.insert(estudio);

      solicitudEstudio.set_estudio(estudioAgregado);
      solicitudEstudio.set_estado("procesado");

      Usuario usuario = Fabrica.crearComandoConId(Usuario.class, 2);
      solicitudEstudio.set_analista(usuario);

      daoSolicitudEstudio.update(solicitudEstudio);

      DaoPregunta daoPregunta = Fabrica.crear(DaoPregunta.class);

      for(PreguntaDto pregunta:estudioDto.getPreguntas()){

        if(pregunta.getId() <= 0 ){

          Pregunta pregun = Fabrica.crear(Pregunta.class);
          pregun.set_descripcionPregunta(pregunta.getDescripcionPregunta());

          if(pregunta.getMin() > pregunta.getMax()){

            throw new LimiteExcepcion("El limite superior no puede ser menor al limite inferior");

          }else {

            pregun.set_min(pregunta.getMin());
            pregun.set_max(pregunta.getMax());

          }

          pregun.set_tipoPregunta(pregunta.getTipoPregunta());

          Pregunta preguntaAgregada = daoPregunta.insert(pregun);

          DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);

          PreguntaEncuesta preguntaEncuesta = Fabrica.crear(PreguntaEncuesta.class);
          preguntaEncuesta.set_pregunta(preguntaAgregada);
          preguntaEncuesta.set_encuesta(encuesta);

          daoPreguntaEncuesta.insert(preguntaEncuesta);

          if(pregun.get_tipoPregunta().equals("desarrollo")){

            DaoOpcion daoOpcion = Fabrica.crear(DaoOpcion.class);
            Integer id = 8;
            Opcion opcion = daoOpcion.find(id.longValue(), Opcion.class);

            DaoPreguntaOpcion daoPreguntaOpcion = Fabrica.crear(DaoPreguntaOpcion.class);
            PreguntaOpcion preguntaOpcion = Fabrica.crear(PreguntaOpcion.class);
            preguntaOpcion.set_pregunta(preguntaAgregada);
            preguntaOpcion.set_opcion(opcion);

            daoPreguntaOpcion.insert(preguntaOpcion);

          }

          pregun.set_id(preguntaAgregada.get_id());

          List<OpcionDto> opcionesDtos = pregunta.getOpciones();
          List<Opcion> opciones = new ArrayList<>();

          if (opcionesDtos != null) {

            for (OpcionDto opcion : opcionesDtos) {

              DaoOpcion dao = Fabrica.crear(DaoOpcion.class);
              Opcion op = Fabrica.crear(Opcion.class);

              op.set_descripcion(opcion.getDescripcion());
              op = dao.insert(op);

              opciones.add(op);
              opcion.setId(op.get_id());

            }

            DaoPreguntaOpcion daoPreguntaOpcion = Fabrica.crear(DaoPreguntaOpcion.class);

            for (Opcion opcion : opciones) {

              PreguntaOpcion preguntaOpcion = Fabrica.crear(PreguntaOpcion.class);
              preguntaOpcion.set_opcion(opcion);
              preguntaOpcion.set_pregunta(preguntaAgregada);

              daoPreguntaOpcion.insert(preguntaOpcion);

            }

            pregun.setOpciones(opciones);

          }

        }else{

          DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);
          PreguntaEncuesta preguntaEncuesta = Fabrica.crear(PreguntaEncuesta.class);
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

      EstudioDto estudioDto = Fabrica.crear(EstudioDto.class);
      estudioDto.setId(estudio.get_id());
      estudioDto.setNombreEstudio(estudio.get_nombreEstudio());

      return estudioDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static EstudioDto MapEntityToEstudioDtoByEncuestado(Estudio estudio){

    try{

      EstudioDto estudioDto = Fabrica.crear(EstudioDto.class);
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

      EstudioDto estudioDto = Fabrica.crear(EstudioDto.class);
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
