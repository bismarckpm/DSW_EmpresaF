package ucab.dsw.dtos;

import java.util.List;

public class PreguntaDto extends DtoBase {

    private String descripcionPregunta;

    private String tipoPregunta;

    private Integer max;

    private Integer min;

    private List<OpcionDto> opciones;

    private List<PreguntaDto> preguntas;

    public PreguntaDto(long id) throws Exception {
        super(id);
    }

    public PreguntaDto() {
    }

    public String getDescripcionPregunta() {
        return descripcionPregunta;
    }

    public void setDescripcionPregunta(String descripcionPregunta) {
        this.descripcionPregunta = descripcionPregunta;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public List<OpcionDto> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<OpcionDto> opciones) {
        this.opciones = opciones;
    }

  public List<PreguntaDto> getPreguntas() {
    return preguntas;
  }

  public void setPreguntas(List<PreguntaDto> preguntas) {
    this.preguntas = preguntas;
  }
}
