package ucab.dsw.servicio;


/*@Path( "/prueba" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class pruebaORMWS extends AplicacionBase {


    @PUT
    @Path("/adduser")
    public UsuarioDto addUser(UsuarioDto usuarioDto) {
        UsuarioDto resultado = new UsuarioDto();
        try {
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = new Usuario();
            usuario.set_nombre(usuarioDto.getNombre());
            usuario.set_apellido(usuarioDto.getApellido());
            usuario.set_estatus(usuarioDto.getEstatus());
            usuario.set_correoelectronico(usuarioDto.getCorreoelectronico());
            TipoUsuario tipoUsuario = new TipoUsuario(usuarioDto.getTipoUsuarioDto().getId());
            usuario.set_tipousuario(tipoUsuario);
            Usuario resul = dao.insert(usuario);
            resultado.setId(resul.get_id());
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/consulta")
    public String consulta() {
        return "Epa hhahahha";
    }

    @GET
    @Path("/getusers")
    public List<Usuario> getUsers() {

        List<Usuario> usuarios = null;
        try {
            DaoUsuario dao = new DaoUsuario();
            usuarios = dao.findAll(Usuario.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
      System.out.println("Persona encontrada: " + usuarios);

        return usuarios;
    }

  @GET
  @Path("/getuser")
  public Response getUser() {
    Usuario usuario = new Usuario();
    try {
      long id = 1 ;
      DaoUsuario dao = new DaoUsuario();
      usuario = dao.find(id, Usuario.class);
    } catch (Exception ex) {
      String problema = ex.getMessage();
    }
    System.out.println("Persona encontrada: " + usuario.get_nombre());
    return Response.ok().entity(usuario).build();
  }

    @PUT
    @Path("/updateUser/{id}")
    public UsuarioDto updateUser(@PathParam("id") long id, UsuarioDto usuarioDto) {
        UsuarioDto resultado = new UsuarioDto();
        try {
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.find(id, Usuario.class);

            if (usuario != null) {
                usuario.set_nombre(usuarioDto.getNombre());
                usuario.set_apellido(usuarioDto.getApellido());
                usuario.set_correoelectronico(usuarioDto.getCorreoelectronico());
                Usuario resul = dao.update(usuario);
                resultado.setId(resul.get_id());
            }

        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return resultado;
    }

    @DELETE
    @Path("/deleteUser/{id}")
    public UsuarioDto deleteUser(@PathParam("id") long id)
    {
        UsuarioDto resultado = new UsuarioDto();
        try
        {
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.find(id, Usuario.class);

            if(usuario != null)
            {
                Usuario resul = dao.delete(usuario);
                resultado.setId(resul.get_id());
            }
        }
        catch (Exception ex){
            String problema = ex.getMessage();
        }
        return resultado;
  }
}*/
