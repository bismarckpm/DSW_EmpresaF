package ucab.dsw.dtos;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PreguntaEncuestaDto extends DtoBase {


  private PreguntaDto pregunta;

  private EncuestaDto encuesta;

  public PreguntaEncuestaDto(long id) throws Exception {
    super(id);
  }

  public PreguntaEncuestaDto() {
  }

  public PreguntaDto getPregunta() {
    return pregunta;
  }

  public void setPregunta(PreguntaDto pregunta) {
    this.pregunta = pregunta;
  }

  public EncuestaDto getEncuesta() {
    return encuesta;
  }

  public void setEncuesta(EncuestaDto encuesta) {
    this.encuesta = encuesta;
  }
}
