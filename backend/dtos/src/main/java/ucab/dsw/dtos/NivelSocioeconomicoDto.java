package ucab.dsw.dtos;


public class NivelSocioeconomicoDto extends  DtoBase{

  private String tipo;

  public NivelSocioeconomicoDto(long id) throws Exception {
    super(id);
  }

  public NivelSocioeconomicoDto() {
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
}
