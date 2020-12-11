package ucab.dsw.dtos;

public class PaisDto extends DtoBase{

  private String nombre;

  public PaisDto(long id) throws Exception {
    super(id);
  }

  public PaisDto() {
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
