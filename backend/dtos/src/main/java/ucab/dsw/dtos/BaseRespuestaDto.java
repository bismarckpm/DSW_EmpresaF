package ucab.dsw.dtos;

import java.util.List;

public class BaseRespuestaDto extends DtoBase{

  private List<ArrayRespuestaDto>  respuestas;
  private SolicitudEstudioDto solicitudEstudioDto;

  public BaseRespuestaDto() {
  }

  public List<ArrayRespuestaDto> getRespuestas() {
    return respuestas;
  }

  public void setRespuestas(List<ArrayRespuestaDto> respuestas) {
    this.respuestas = respuestas;
  }

  public SolicitudEstudioDto getSolicitudEstudioDto() {
    return solicitudEstudioDto;
  }

  public void setSolicitudEstudioDto(SolicitudEstudioDto solicitudEstudioDto) {
    this.solicitudEstudioDto = solicitudEstudioDto;
  }
}
