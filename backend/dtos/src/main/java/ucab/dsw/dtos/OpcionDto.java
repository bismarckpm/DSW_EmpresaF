package ucab.dsw.dtos;

import java.util.List;

public class OpcionDto extends DtoBase{

    // Constructors
    
    public OpcionDto() {
    }
    
    public OpcionDto(long id) throws Exception {
      super(id);
    }
    
    // Properties

    private String descripcion;
    
    private List<PreguntaOpcionDto> preguntaOpcion;
    
    private List<RespuestaDto> respuestas;
    
    
    // Getters & Setters

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PreguntaOpcionDto> getPreguntaOpcion() {
        return preguntaOpcion;
    }

    public void setPreguntaOpcion(List<PreguntaOpcionDto> preguntaOpcion) {
        this.preguntaOpcion = preguntaOpcion;
    }

    public List<RespuestaDto> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaDto> respuestas) {
        this.respuestas = respuestas;
    }
    
}
