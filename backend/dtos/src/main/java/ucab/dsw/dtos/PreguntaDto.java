package ucab.dsw.dtos;

import java.util.List;

public class PreguntaDto extends DtoBase{
    // Constructors
    
    public PreguntaDto() {
    }
    
    public PreguntaDto(long id) throws Exception {
      super(id);
    }
    
    
    // Properties 
    
    private String descripcion;
    
    private String tipoPregunta;
    
    private float max;
    
    private float min;
    
    private List<PreguntaEncuestaDto> preguntaEncuesta;
    
    private List<PreguntaOpcionDto> preguntaOpcion;
    
    
    // Getters & Setters

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public List<PreguntaEncuestaDto> getPreguntaEncuesta() {
        return preguntaEncuesta;
    }

    public void setPreguntaEncuesta(List<PreguntaEncuestaDto> preguntaEncuesta) {
        this.preguntaEncuesta = preguntaEncuesta;
    }

    public List<PreguntaOpcionDto> getPreguntaOpcion() {
        return preguntaOpcion;
    }

    public void setPreguntaOpcion(List<PreguntaOpcionDto> preguntaOpcion) {
        this.preguntaOpcion = preguntaOpcion;
    }
    
}
