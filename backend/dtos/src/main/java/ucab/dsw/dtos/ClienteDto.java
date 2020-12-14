package ucab.dsw.dtos;

public class ClienteDto extends DtoBase{

  private String nombre;
  private String estado;

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

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }
}
