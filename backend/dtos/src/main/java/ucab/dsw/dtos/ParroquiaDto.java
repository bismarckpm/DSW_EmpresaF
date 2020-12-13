package ucab.dsw.dtos;


public class ParroquiaDto extends DtoBase{

  private String nombreParroquia;


  private MunicipioDto municipio;

  public ParroquiaDto(long id) throws Exception {
    super(id);
  }

  public ParroquiaDto() {
  }

  public String getNombreParroquia() {
    return nombreParroquia;
  }

  public void setNombreParroquia(String nombreParroquia) {
    this.nombreParroquia = nombreParroquia;
  }

  public MunicipioDto getMunicipio() {
    return municipio;
  }

  public void setMunicipio(MunicipioDto municipio) {
    this.municipio = municipio;
  }
}
