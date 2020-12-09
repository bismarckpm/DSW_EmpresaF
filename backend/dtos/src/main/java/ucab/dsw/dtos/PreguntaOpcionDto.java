package ucab.dsw.dtos;

public class PreguntaOpcionDto extends DtoBase{
    // Constructors
    
    public PreguntaOpcionDto() {
    }
    
    public PreguntaOpcionDto(long id) throws Exception {
      super(id);
    }
    
    
    // Properties 
    
    private PreguntaDto pregunta;

    private OpcionDto opcion;
    
    
    // Getters & Setters

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
