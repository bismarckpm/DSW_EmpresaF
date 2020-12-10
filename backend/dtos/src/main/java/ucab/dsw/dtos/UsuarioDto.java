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
}
