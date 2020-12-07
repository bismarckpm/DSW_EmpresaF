package ucab.dsw.dtos;


import java.util.List;

public class CategoriaDto extends DtoBase{


  private String nombreCategoria;


  private List<SubcategoriaDto> subcategorias;

  public CategoriaDto(long id) throws Exception {
    super(id);
  }

  public CategoriaDto() {
  }

  public String getNombreCategoria() {
    return nombreCategoria;
  }

  public void setNombreCategoria(String nombreCategoria) {
    this.nombreCategoria = nombreCategoria;
  }

  public List<SubcategoriaDto> getSubcategorias() {
    return subcategorias;
  }

  public void setSubcategorias(List<SubcategoriaDto> subcategorias) {
    this.subcategorias = subcategorias;
  }
}
