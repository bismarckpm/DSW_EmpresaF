package ucab.dsw.directorioactivo;

import ucab.dsw.dtos.UsuarioDto;

import javax.naming.*;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class DirectorioActivo
{

    private DirContext _ldapContext;
    private String _url = "ldap://127.0.0.1:10389";
    private String _connType =  "simple";
    private String _directory =  "ou=users,o=pruebaucab";
    private String _userDirectory =  "cn=%s";
    private String _user =  "admin";
    private String _password =  "secret";

    /*
    Method to connect with the ldap
     */
    public DirectorioActivo()
    {}

    private void connectLDAP(String user, String password)
    {
        try
        {
            Hashtable<String, String> environment = new Hashtable<String, String>();
            environment.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory" );
            environment.put( Context.PROVIDER_URL, _url );
            environment.put( Context.SECURITY_AUTHENTICATION, _connType );
            environment.put( Context.SECURITY_PRINCIPAL, String.format( "uid=%s,ou=system", user ) );
            environment.put( Context.SECURITY_CREDENTIALS, password );
            _ldapContext = new InitialDirContext( environment );
        }
        catch ( Exception e )
        {

        }
    }

  private DirContext AuthLDAP(String user, String password)
  {
    try
    {
      Hashtable<String, String> environment = new Hashtable<String, String>();
      environment.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory" );
      environment.put( Context.PROVIDER_URL, _url );
      environment.put( Context.SECURITY_AUTHENTICATION, _connType );
      environment.put( Context.SECURITY_PRINCIPAL, String.format( "cn=%s,ou=users,o=pruebaucab", user ) );
      environment.put( Context.SECURITY_CREDENTIALS, password );
      _ldapContext = new InitialDirContext( environment );
    }
    catch ( Exception e )
    {

    }

    return _ldapContext;
  }

    private void disconnectLDAP()
    {
        try
        {
            _ldapContext.close();
        }
        catch ( Exception e )
        {

        }
    }

    /*
      Method that adds users to ldap
     */
    public void addEntryToLdap(UsuarioDto user, String rol)
    {

        try
        {
            connectLDAP( _user, _password );
            Attribute oc = new BasicAttribute( "objectClass" );
            oc.add( "top" );
            oc.add( "person" );
            SimpleDateFormat format = new SimpleDateFormat( "yyyyMMddHHmm" );
            BasicAttributes entry = new BasicAttributes();
            entry.put( oc );
            entry.put( new BasicAttribute( "cn", user.getNombreUsuario() ) );
            entry.put( new BasicAttribute( "sn", user.getNombreUsuario() ) );
            entry.put( new BasicAttribute( "description", rol ) );
            entry.put( new BasicAttribute( "userpassword", user.getContrasena() ) );
            entry.put( new BasicAttribute( "pwdLastSuccess", format.format( new Date() ) + "Z" ) );
            _ldapContext.createSubcontext( String.format( _userDirectory + "," + _directory, user.getNombreUsuario()), entry );

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /*
     Method that remove users to ldap
    */
    public void deleteEntry(UsuarioDto user)
    {
        try
        {
            connectLDAP( _user, _password );
            _ldapContext.destroySubcontext( String.format(_userDirectory + "," + _directory, user.getNombreUsuario()));
        }
        catch ( Exception exception )
        {
            exception.printStackTrace();
        }
        finally
        {
            disconnectLDAP();
        }
    }

    /*
     Method that obtains user data from ldap
    */
    public String getEntry(UsuarioDto user)
    {
      String rol = null;
        try
        {
            connectLDAP( _user, _password );
            SearchControls searcCon = new SearchControls();
            searcCon.setSearchScope( SearchControls.SUBTREE_SCOPE );
            NamingEnumeration results =
                    _ldapContext.search( _directory, String.format(_userDirectory, user.getNombreUsuario()), searcCon );
            if ( results != null )
            {
                while ( results.hasMore() )
                {
                    SearchResult res = ( SearchResult ) results.next();
                    Attributes atbs = res.getAttributes();
                    Attribute atb = atbs.get( "description" );
                    rol = ( String ) atb.get();
                }
            }
            else
            {
                System.out.println( "fail" );
            }
        }
        catch ( Exception exception )
        {
            exception.printStackTrace();
        }
        finally
        {
            disconnectLDAP();
        }
        return rol;
    }

    /*
    Method that updates the user in the ldap
     */
    public void updateEntry(UsuarioDto user)
    {
        try
        {
            connectLDAP( _user, _password );
            Attributes atbs = new BasicAttributes();
            Attribute atb = new BasicAttribute("mail","java2db@mai.com");
            atbs.put(atb);

            _ldapContext.modifyAttributes( String.format(_userDirectory + "," + _directory, user.getNombreUsuario())
                    , DirContext.REPLACE_ATTRIBUTE,atbs );
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            disconnectLDAP();
        }
    }


    public void changePassword(UsuarioDto user)
    {
        try
        {
            connectLDAP( _user, _password );
            ModificationItem[] modificationItems = new ModificationItem[ 1 ];
            modificationItems[ 0 ] = new ModificationItem( DirContext.REPLACE_ATTRIBUTE,
                                                           new BasicAttribute( "userpassword", user.getContrasena()
                                                           ) );
            _ldapContext.modifyAttributes(String.format(_userDirectory + "," + _directory, user.getNombreUsuario()), modificationItems );
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            disconnectLDAP();
        }
    }

    public boolean userAuthentication(UsuarioDto user) {
        boolean isAuth = false;
      try
        {
            if(AuthLDAP(user.getNombreUsuario(), user.getContrasena()) != null){
              isAuth = true;
            };
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            disconnectLDAP();
        }
      return isAuth;
    }
}
