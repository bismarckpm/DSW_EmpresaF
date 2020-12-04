package ucab.dsw.dtos;

public class ClienteDto extends DtoBase{

  private String nombre;
  private UsuarioDto usuarioDto;

  public ClienteDto(long id) throws Exception {
    super(id);
  }

  public ClienteDto() {
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public UsuarioDto getUsuarioDto() {
    return usuarioDto;
  }

  public void setUsuarioDto(UsuarioDto usuarioDto) {
    this.usuarioDto = usuarioDto;
  }
}
