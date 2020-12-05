package ucab.dsw.dtos;


public class ParroquiaDto extends DtoBase{

  private String nombreMunicipio;


  private MunicipioDto municipio;

  public ParroquiaDto(long id) throws Exception {
    super(id);
  }

  public ParroquiaDto() {
  }

  public String getNombreMunicipio() {
    return nombreMunicipio;
  }

  public void setNombreMunicipio(String nombreMunicipio) {
    this.nombreMunicipio = nombreMunicipio;
  }

  public MunicipioDto getMunicipio() {
    return municipio;
  }

  public void setMunicipio(MunicipioDto municipio) {
    this.municipio = municipio;
  }
}
