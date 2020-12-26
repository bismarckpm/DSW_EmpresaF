package ucab.dsw.dtos;

import java.util.List;

public class ArrayRespuestaDto {

  private PreguntaDto pregunta;
  private String descripcion;
  private String rango;
  private EncuestadoDto encuestado;
  private List<OpcionDto> opciones;

  public PreguntaDto getPregunta() {
    return pregunta;
  }

  public void setPregunta(PreguntaDto pregunta) {
    this.pregunta = pregunta;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getRango() {
    return rango;
  }

  public void setRango(String rango) {
    this.rango = rango;
  }

  public EncuestadoDto getEncuestado() {
    return encuestado;
  }

  public void setEncuestado(EncuestadoDto encuestado) {
    this.encuestado = encuestado;
  }

  public List<OpcionDto> getOpciones() {
    return opciones;
  }

  public void setOpciones(List<OpcionDto> opciones) {
    this.opciones = opciones;
  }

  public ArrayRespuestaDto() {
  }
}
