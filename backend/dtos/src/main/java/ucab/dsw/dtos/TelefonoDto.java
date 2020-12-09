package ucab.dsw.dtos;


public class TelefonoDto extends DtoBase{

  private String codigoArea;

  private String numeroTelefono;

  private EncuestadoDto encuestado;

  public TelefonoDto(long id) throws Exception {
    super(id);
  }

  public TelefonoDto() {
  }

  public String getCodigoArea() {
    return codigoArea;
  }

  public void setCodigoArea(String codigoArea) {
    this.codigoArea = codigoArea;
  }

  public String getNumeroTelefono() {
    return numeroTelefono;
  }

  public void setNumeroTelefono(String numeroTelefono) {
    this.numeroTelefono = numeroTelefono;
  }

  public EncuestadoDto getEncuestado() {
    return encuestado;
  }

  public void setEncuestado(EncuestadoDto encuestado) {
    this.encuestado = encuestado;
  }
}
