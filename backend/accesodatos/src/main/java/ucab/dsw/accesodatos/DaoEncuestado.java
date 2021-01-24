package ucab.dsw.accesodatos;

import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.SolicitudEstudio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoEncuestado extends Dao<Encuestado>
{
  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoEncuestado( )
  {
    super( _handler );
    this._em=_handler.getSession();
  }

  public List<Encuestado> getUsersMuestra(SolicitudEstudio solicitudEstudio){
    try {
      TypedQuery<Encuestado> encuestados = this._em.createNamedQuery("getUsersMuestra", Encuestado.class);
      encuestados.setParameter("parroquiaId", solicitudEstudio.get_parroquia())
        .setParameter("nivelId", solicitudEstudio.get_nivelSocioeconomico()).getResultList();

      List<Encuestado> resultado = encuestados.getResultList();
      return resultado;
    }
    catch (Exception ex){
      ex.printStackTrace();
      return  null;
    }
  }

  public List<Encuestado> getUsersMuestraByGenero(SolicitudEstudio solicitudEstudio) {
    try {
      TypedQuery<Encuestado> encuestados = this._em.createNamedQuery("getUsersMuestraByGenero", Encuestado.class);
      encuestados.setParameter("parroquiaId", solicitudEstudio.get_parroquia())
        .setParameter("nivelId", solicitudEstudio.get_nivelSocioeconomico())
        .setParameter("genero", solicitudEstudio.get_genero()).getResultList();

      List<Encuestado> resultado = encuestados.getResultList();
      return resultado;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }
}
