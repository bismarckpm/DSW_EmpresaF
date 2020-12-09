package ucab.dsw.dtos;


import java.util.List;

public class OpcionDto extends DtoBase{


  private String descripcion;


  private List<PreguntaOpcionDto> preguntasOpciones;

  public OpcionDto(long id) throws Exception {
    super(id);
  }

  public OpcionDto() {
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public List<PreguntaOpcionDto> getPreguntasOpciones() {
    return preguntasOpciones;
  }

  public void setPreguntasOpciones(List<PreguntaOpcionDto> preguntasOpciones) {
    this.preguntasOpciones = preguntasOpciones;
  }
}
