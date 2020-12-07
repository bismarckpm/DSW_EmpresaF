package ucab.dsw.dtos;

import javax.persistence.*;
import java.util.List;

public class SubcategoriaDto extends DtoBase{

  private String _nombreSubcategoria;

  private CategoriaDto categoria;

  private List<MarcaDto> marcas;

  public SubcategoriaDto(long id) throws Exception {
    super(id);
  }

  public SubcategoriaDto() {
  }

  public String get_nombreSubcategoria() {
    return _nombreSubcategoria;
  }

  public void set_nombreSubcategoria(String _nombreSubcategoria) {
    this._nombreSubcategoria = _nombreSubcategoria;
  }

  public CategoriaDto getCategoria() {
    return categoria;
  }

  public void setCategoria(CategoriaDto categoria) {
    this.categoria = categoria;
  }

  public List<MarcaDto> getMarcas() {
    return marcas;
  }

  public void setMarcas(List<MarcaDto> marcas) {
    this.marcas = marcas;
  }
}
