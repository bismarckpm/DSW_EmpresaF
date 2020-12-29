package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "respuesta")
public class Respuesta extends EntidadBase {

    @Id
    @Column(name = "codigo_respuesta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;

    @Column(name = "fecha")
    private Date _fecha;

    @Column(name = "descripcion")
    private String _descripcion;

    @Column(name = "rango")
    private Integer _rango;

    @ManyToOne
    @JoinColumn(name = "fk_encuestado")
    private Encuestado _encuestado;

    @ManyToOne
    @JoinColumn(name = "fk_pregunta_encuesta")
    private PreguntaEncuesta _preguntaEncuesta;

    @OneToMany(mappedBy = "_respuesta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<RespuestaOpcion> _respuestasOpciones;

    public Respuesta() {

    }

    public Respuesta(long _id) {
        this._id = _id;
    }

    @Override
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public Date get_fecha() {
        return _fecha;
    }

    public void set_fecha(Date _fecha) {
        this._fecha = _fecha;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

  public Integer get_rango() {
    return _rango;
  }

  public void set_rango(Integer _rango) {
    this._rango = _rango;
  }

  public Encuestado get_encuestado() {
        return _encuestado;
    }

    public void set_encuestado(Encuestado _encuestado) {
        this._encuestado = _encuestado;
    }

    public PreguntaEncuesta get_preguntaEncuesta() {
        return _preguntaEncuesta;
    }

    public void set_preguntaEncuesta(PreguntaEncuesta _preguntaEncuesta) {
        this._preguntaEncuesta = _preguntaEncuesta;
    }

    public List<RespuestaOpcion> get_respuestasOpciones() {
        return _respuestasOpciones;
    }

    public void set_respuestasOpciones(List<RespuestaOpcion> _respuestasOpciones) {
        this._respuestasOpciones = _respuestasOpciones;
    }

    public List<Opcion> get_opciones() {
        return get_respuestasOpciones()
                .stream()
                .map(respuestaOpcion -> respuestaOpcion.get_opcion())
                .collect(Collectors.toList());
    }

}
