import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;

import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

public class pruebaDirectorioActivo
{
    @Test
    public void createUserLDAP()
    {
        UsuarioDto user = new UsuarioDto();
        user.setNombreUsuario( "administrador3" );
        user.setContrasena( "12345" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.addEntryToLdap( user, "administrador" );
    }

    @Test
    public void deleteUserLDAP()
    {
        UsuarioDto user = new UsuarioDto();
        user.setNombreUsuario( "bismarckpm2" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.deleteEntry( user );
    }

    @Test
    public void getUserLDAP()
    {
        UsuarioDto user = new UsuarioDto();
        user.setNombreUsuario( "bismarckpm2" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.getEntry( user );
    }

    @Test
    public void changePassword() throws NamingException {
        UsuarioDto user = new UsuarioDto();
        user.setNombreUsuario( "bismarckpm2" );
        user.setContrasena( "MARIAPEPE123" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.changePassword( user );
    }

    @Test
    public void userAuthentication()
    {
        UsuarioDto user = new UsuarioDto();
        user.setNombreUsuario( "bismarckpm2");
        user.setContrasena( "MARIAPEPE" );
        DirectorioActivo ldap = new DirectorioActivo();


        Assert.assertTrue(ldap.userAuthentication(user));
    }
}
