package ucab.dsw.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

public class PreguntaEncuesta implements Serializable{
    
    // Constructors
    
    public PreguntaEncuesta() {
    }
    
    public PreguntaEncuesta(long id) {
        _id = id;
    }
    
    
    // Properties
    
    @Id
    @Column( name = "codigo_preg_encu" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;
    
    @ManyToOne
    @JoinColumn(name = "fk_pregunta", nullable = false)
    private Pregunta _pregunta;
    
    @ManyToOne
    @JoinColumn(name = "fk_encuesta")
    private Encuesta _encuesta;
    
    @OneToMany(mappedBy = "_preguntaEncuesta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Respuesta> _respuestas;
    
    
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

    public Encuesta getEncuesta() {
        return _encuesta;
    }

    public void setEncuesta(Encuesta _encuesta) {
        this._encuesta = _encuesta;
    }

    public List<Respuesta> getRespuestas() {
        return _respuestas;
    }

    public void setRespuestas(List<Respuesta> _respuestas) {
        this._respuestas = _respuestas;
    }
    
}
