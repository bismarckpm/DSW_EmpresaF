package ucab.dsw.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PreguntaOpcion implements Serializable{
    
    // Constructors
    
    public PreguntaOpcion() {
    }
    
    public PreguntaOpcion(long id) {
        _id = id;
    }
    
    
    // Properties
    
    @Id
    @Column( name = "codigo_pregunta_opcion" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;
    
    @ManyToOne
    @JoinColumn(name = "fk_pregunta", nullable = false)
    private Pregunta _pregunta;
    
    @ManyToOne
    @JoinColumn(name = "fk_opcion", nullable = false)
    private Opcion _opcion;
    
    
    // Getters & Setters

    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public Pregunta getPregunta() {
        return _pregunta;
    }

    public void setPregunta(Pregunta _pregunta) {
        this._pregunta = _pregunta;
    }

    public Opcion getOpcion() {
        return _opcion;
    }

    public void setOpcion(Opcion _opcion) {
        this._opcion = _opcion;
    }
    
}
