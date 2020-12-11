package ucab.dsw.dtos;


public class EncuestaDto extends  DtoBase{

  private SubcategoriaDto subcategoria;

  public EncuestaDto(long id) throws Exception {
    super(id);
  }

  public EncuestaDto() {
  }

  public SubcategoriaDto getSubcategoria() {
    return subcategoria;
  }

  public void setSubcategoria(SubcategoriaDto subcategoria) {
    this.subcategoria = subcategoria;
  }
}
