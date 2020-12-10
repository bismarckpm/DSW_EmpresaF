package ucab.dsw.accesodatos;


import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;

import javax.persistence.EntityManager;
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
}
