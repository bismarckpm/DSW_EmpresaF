package ucab.dsw.dtos;


public class NivelEstudioDto extends DtoBase{

  private String nombre;

  public NivelEstudioDto(long id) throws Exception {
    super(id);
  }

  public NivelEstudioDto() {
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
