package ucab.dsw.dtos;


public class PreguntaOpcionDto extends DtoBase{

  private PreguntaDto pregunta;


  private OpcionDto opcion;

  public PreguntaOpcionDto(long id) throws Exception {
    super(id);
  }

  public PreguntaOpcionDto() {
  }

  public PreguntaDto getPregunta() {
    return pregunta;
  }

  public void setPregunta(PreguntaDto pregunta) {
    this.pregunta = pregunta;
  }

  public OpcionDto getOpcion() {
    return opcion;
  }

  public void setOpcion(OpcionDto opcion) {
    this.opcion = opcion;
  }
}
