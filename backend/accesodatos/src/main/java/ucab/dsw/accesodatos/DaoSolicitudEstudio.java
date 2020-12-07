package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;

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

  public SolicitudEstudio getSolicitudesByCaracteristicas (SolicitudEstudio solicitud){
    SolicitudEstudio resultado = null;
    try {
      resultado = this._em.createNamedQuery("getSolicitudesByCaracteristicas", SolicitudEstudio.class).
        setParameter("clienteId", solicitud.get_cliente()).
        setParameter("marcaId", solicitud.get_marca()).
        setParameter("edadInicial", solicitud.get_edadInicial()).
        setParameter("edadFinal", solicitud.get_edadfinal()).
        setParameter("parroquiaId", solicitud.get_parroquia()).
        setParameter("nivelSocioeconomico", solicitud.get_nivelSocioeconomico()).getSingleResult();
    }
    catch (Exception ex){
      return null;
    }

    return resultado;
  }
}
