package ucab.dsw.dtos;


public class EncuestaDto extends  DtoBase{

  private SubcategoriaDto subcategoria;
  private String nombreEncuesta;

  public EncuestaDto(long id) throws Exception {
    super(id);
  }

  public EncuestaDto() {
  }


  public String getNombreEncuesta() {
    return nombreEncuesta;
  }

  public void setNombreEncuesta(String nombreEncuesta) {
    this.nombreEncuesta = nombreEncuesta;
  }

  public SubcategoriaDto getSubcategoria() {
    return subcategoria;
  }

  public void setSubcategoria(SubcategoriaDto subcategoria) {
    this.subcategoria = subcategoria;
  }
}
