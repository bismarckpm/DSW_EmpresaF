package ucab.dsw.entidades;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "pregunta_encuesta")
public class PreguntaEncuesta extends EntidadBase{

    @Id
    @Column(name = "codigo_preg_encu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;

    @ManyToOne
    @JoinColumn(name = "fk_pregunta")
    private Pregunta _pregunta;

    @ManyToOne
    @JoinColumn(name = "fk_encuesta")
    private Encuesta _encuesta;

    @OneToMany(mappedBy = "_preguntaEncuesta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Respuesta> _respuestas;

    public PreguntaEncuesta(long _id) {
        this._id = _id;
    }

    public PreguntaEncuesta() {
    }

    @Override
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public Pregunta get_pregunta() {
        return _pregunta;
    }

    public void set_pregunta(Pregunta _pregunta) {
        this._pregunta = _pregunta;
    }

    public Encuesta get_encuesta() {
        return _encuesta;
    }

    public void set_encuesta(Encuesta _encuesta) {
        this._encuesta = _encuesta;
    }

    public List<Respuesta> get_respuestas() {
        return _respuestas;
    }
    
    public void set_respuestas(List<Respuesta> _respuestas) {
        this._respuestas = _respuestas;
    }
}
