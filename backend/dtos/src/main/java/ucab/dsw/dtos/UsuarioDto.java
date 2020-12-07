package ucab.dsw.dtos;

public class UsuarioDto extends DtoBase
{
    private String nombreUsuario;
    private String contrasena;
    private String rol;
    private ClienteDto clienteDto;

  public UsuarioDto(long id) throws Exception {
    super(id);
  }

  public UsuarioDto() {
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }

  public void setNombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
  }

  public String getContrasena() {
    return contrasena;
  }

  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }

  public ClienteDto getClienteDto() {
    return clienteDto;
  }

  public void setClienteDto(ClienteDto clienteDto) {
    this.clienteDto = clienteDto;
  }

  private EncuestadoDto encuestadoDto;

  public EncuestadoDto getEncuestadoDto() {
    return encuestadoDto;
  }

  public void setEncuestadoDto(EncuestadoDto encuestadoDto) {
    this.encuestadoDto = encuestadoDto;
  }

  public String getRol() {
    return rol;
  }

  public void setRol(String rol) {
    this.rol = rol;
  }

  /*public String getNombreUsuario()
    {
        return nombre;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    public String getCorreoelectronico()
    {
        return correoelectronico;
    }

    public void setCorreoelectronico( String correoelectronico )
    {
        this.correoelectronico = correoelectronico;
    }

    public TipoUsuarioDto getTipoUsuarioDto()
    {
        return tipoUsuarioDto;
    }

    public void setTipoUsuarioDto( TipoUsuarioDto tipoUsuarioDto )
    {
        this.tipoUsuarioDto = tipoUsuarioDto;
    }

    private String apellido;
    private String correoelectronico;
    private TipoUsuarioDto tipoUsuarioDto;

    public String getContrasena()
    {
        return contrasena;
    }

    public void setContrasena( String contrasena )
    {
        this.contrasena = contrasena;
    }

    private String contrasena;

    public String getEstatus()
    {
        return estatus;
    }

    public void setEstatus( String estatus )
    {
        this.estatus = estatus;
    }

    private String estatus;*/
}
