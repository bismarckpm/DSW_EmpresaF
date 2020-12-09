package ucab.dsw.dtos;

import java.util.List;

public class PreguntaEncuestaDto extends DtoBase{
    
    // Constructors
    public PreguntaEncuestaDto() {
    }
    
    public PreguntaEncuestaDto(long id) throws Exception {
      super(id);
    }
    
    
    // Properties
    
    private PreguntaDto pregunta;
    
    private EncuestaDto encuesta;
    
    private List<RespuestaDto> respuestas;
    
    
    // Getters & Setters

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

    public List<RespuestaDto> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaDto> respuestas) {
        this.respuestas = respuestas;
    }
    
}
