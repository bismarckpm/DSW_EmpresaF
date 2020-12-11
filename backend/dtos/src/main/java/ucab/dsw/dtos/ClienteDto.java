package ucab.dsw.dtos;

public class ClienteDto extends DtoBase{

  private String nombre;

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

}
