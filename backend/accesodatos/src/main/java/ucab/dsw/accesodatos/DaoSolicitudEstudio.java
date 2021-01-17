package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoSolicitudEstudio extends  Dao<SolicitudEstudio>{
  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoSolicitudEstudio( )
  {
    super( _handler );
    this._em= _handler.getSession();
  }

  public List<SolicitudEstudio> getSolicitudesByCaracteristicas (SolicitudEstudio solicitud){

    try {
       TypedQuery<SolicitudEstudio> array = this._em.createNamedQuery("getSolicitudesByCaracteristicas", SolicitudEstudio.class);
        array.setParameter("clienteId", solicitud.get_cliente()).
        setParameter("subcategoriaId", solicitud.get_subcategoria()).
        setParameter("edadInicial", solicitud.get_edadInicial()).
        setParameter("edadFinal", solicitud.get_edadfinal()).
        setParameter("parroquiaId", solicitud.get_parroquia()).
        setParameter("genero", solicitud.get_genero()).
        setParameter("nivelSocioeconomico", solicitud.get_nivelSocioeconomico()).getResultList();

      List<SolicitudEstudio> resultado = array.getResultList();
      return resultado;
    }
    catch (Exception ex){
      return null;
    }
  }

  public  List<SolicitudEstudio> getSolicitudesPendientesByAdmin(Long administradorId){
    String estado = "solicitado";
    try {
      TypedQuery <SolicitudEstudio> solicitudes = this._em.createNamedQuery("getSolicitudesPendientesByAdmin", SolicitudEstudio.class);
        solicitudes.setParameter("administradorId", administradorId).getResultList();

      List<SolicitudEstudio> resultado = solicitudes.getResultList();
      return resultado;
    }
    catch (Exception ex){
      return null;
    }
  }
}
