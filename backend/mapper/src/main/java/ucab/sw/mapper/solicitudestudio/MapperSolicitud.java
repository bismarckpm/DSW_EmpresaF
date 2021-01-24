package ucab.sw.mapper.solicitudestudio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;

import java.util.List;

public class MapperSolicitud {

  public static SolicitudEstudio MapSolicitudDtoToEntityAdd(SolicitudEstudioDto solicitudEstudioDto) throws LimiteExcepcion, SolicitudPendienteExcepcion {

    try {

      SolicitudEstudio solicitudEstudio = Fabrica.crear(SolicitudEstudio.class);
      solicitudEstudio.set_estado("solicitado");

      if(solicitudEstudioDto.getEdadInicial() > solicitudEstudioDto.getEdadfinal()){

        throw new LimiteExcepcion("El limite superior no puede ser menor al limite inferior");

      }else {

        solicitudEstudio.set_edadInicial(solicitudEstudioDto.getEdadInicial());
        solicitudEstudio.set_edadfinal(solicitudEstudioDto.getEdadfinal());

      }

      solicitudEstudio.set_genero(solicitudEstudioDto.getGenero());

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      Usuario cliente = daoUsuario.find(solicitudEstudioDto.getCliente().getId(), Usuario.class);
      solicitudEstudio.set_cliente(cliente);

      DaoParroquia daoParroquia = Fabrica.crear(DaoParroquia.class);
      Parroquia parroquia = daoParroquia.find(solicitudEstudioDto.getParroquia().getId(), Parroquia.class);
      solicitudEstudio.set_parroquia(parroquia);

      DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
      Subcategoria subcategoria = daoSubcategoria.find(solicitudEstudioDto.getSubcategoria().getId(), Subcategoria.class);
      solicitudEstudio.set_subcategoria(subcategoria);

      DaoNivelSocioeconomico daoNivelSocioeconomico =  Fabrica.crear(DaoNivelSocioeconomico.class);
      NivelSocioeconomico nivelSocioeconomico = daoNivelSocioeconomico.find(solicitudEstudioDto.getNivelSocioeconomico().getId(), NivelSocioeconomico.class);
      solicitudEstudio.set_nivelSocioeconomico(nivelSocioeconomico);

      List<SolicitudEstudio> solicitudesExistentes = obtenerSolicitudesPrevias(solicitudEstudio);

      if( !solicitudesExistentes.isEmpty()){

        for(SolicitudEstudio sol:solicitudesExistentes){
          if(sol.get_estado().equals("solicitado")){
            throw new SolicitudPendienteExcepcion("Ya posee una solicitud en espera con las mismas caracteristicas");
          }
        }

        for(SolicitudEstudio soli:solicitudesExistentes){
          solicitudEstudio.set_analista(soli.get_analista());
          break;
        }

      }else{
        DaoUsuario dao = Fabrica.crear(DaoUsuario.class);
        Integer id = 4;
        Usuario usuario = dao.find(id.longValue(), Usuario.class);
        solicitudEstudio.set_administrador(usuario);
      }

      return solicitudEstudio;

    }catch (Exception ex){
      throw ex;
    }

  }

  public static SolicitudEstudioDto MapEntityToSolicitudDto(SolicitudEstudio solicitudEstudio){

    try{

      SolicitudEstudioDto solicitudEstudioDto = Fabrica.crear(SolicitudEstudioDto.class);
      solicitudEstudioDto.setId(solicitudEstudio.get_id());
      solicitudEstudioDto.setEdadInicial(solicitudEstudio.get_edadInicial());
      solicitudEstudioDto.setEdadfinal(solicitudEstudio.get_edadfinal());
      solicitudEstudioDto.setGenero(solicitudEstudio.get_genero());
      solicitudEstudioDto.setEstado(solicitudEstudio.get_estado());

      return solicitudEstudioDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }


  /**
   * Metodo para obtener las solicituds de estudio previa con las mismas caracteristicas
   *
   *
   * @param solicitudEstudio solicitud de estudio recien agregada
   * @return List SolicitudEstudio
   */
  private static List<SolicitudEstudio> obtenerSolicitudesPrevias(SolicitudEstudio solicitudEstudio) {

    DaoSolicitudEstudio dao = new DaoSolicitudEstudio();
    List<SolicitudEstudio> solicitudEstudioPrevia = dao.getSolicitudesByCaracteristicas(solicitudEstudio);

    if (solicitudEstudioPrevia != null) {

      return solicitudEstudioPrevia;

    } else {

      return null;

    }

  }

}
