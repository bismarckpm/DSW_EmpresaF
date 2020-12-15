package ucab.dsw.dtos;


public class MarcaDto extends DtoBase{

  private String nombreMarca;

  private String tipoMarca;

  private Double capacidad;

  private String unidad;

  private SubcategoriaDto subcategoria;

  private String marca;

  public MarcaDto(long id) throws Exception {
    super(id);
  }

  public MarcaDto() {
  }

  public String getNombreMarca() {
    return nombreMarca;
  }

  public void setNombreMarca(String nombreMarca) {
    this.nombreMarca = nombreMarca;
  }

  public String getTipoMarca() {
    return tipoMarca;
  }

  public void setTipoMarca(String tipoMarca) {
    this.tipoMarca = tipoMarca;
  }

  public Double getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(Double capacidad) {
    this.capacidad = capacidad;
  }

  public String getUnidad() {
    return unidad;
  }

  public void setUnidad(String unidad) {
    this.unidad = unidad;
  }

  public SubcategoriaDto getSubcategoria() {
    return subcategoria;
  }

  public void setSubcategoria(SubcategoriaDto subcategoria) {
    this.subcategoria = subcategoria;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }
}
