package ucab.dsw.dtos;

import javax.persistence.*;
import java.util.List;

public class SubcategoriaDto extends DtoBase{

  private String nombreSubcategoria;

  private CategoriaDto categoria;

  private String estado;

  private List<MarcaDto> marcas;

  public SubcategoriaDto(long id) throws Exception {
    super(id);
  }

  public SubcategoriaDto() {
  }

  public String getNombreSubcategoria() {
    return nombreSubcategoria;
  }

  public void setNombreSubcategoria(String nombreSubcategoria) {
    this.nombreSubcategoria = nombreSubcategoria;
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

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }
}
