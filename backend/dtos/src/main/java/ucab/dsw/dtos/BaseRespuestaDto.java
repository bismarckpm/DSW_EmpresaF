package ucab.dsw.dtos;

import java.util.List;

public class BaseRespuestaDto extends DtoBase{

  private List<ArrayRespuestaDto>  respuestas;

  public BaseRespuestaDto() {
  }

  public List<ArrayRespuestaDto> getRespuestas() {
    return respuestas;
  }

  public void setRespuestas(List<ArrayRespuestaDto> respuestas) {
    this.respuestas = respuestas;
  }
}
