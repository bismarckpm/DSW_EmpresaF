package ucab.dsw.accesodatos;


import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoUsuario extends Dao<Usuario>
{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoUsuario( )
    {
        super( _handler );
    }

    public Integer getUsuarioByNombreUsuario(UsuarioDto usuario){

      try{
        Query queryUsuario = this._em.createNamedQuery("getUsuarioByNombreUsuario");
        queryUsuario.setParameter("nombreUsuario", usuario.getNombreUsuario());

        Integer resultado = queryUsuario.getFirstResult();
        return resultado;
      }
      catch (Exception ex){
        return null;
      }

    }
}
