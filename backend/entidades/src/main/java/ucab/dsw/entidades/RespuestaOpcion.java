package ucab.dsw.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "respuesta_opcion")
public class RespuestaOpcion extends EntidadBase {

    @Id
    @Column(name = "codigo_resp_opci")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;

    @ManyToOne
    @JoinColumn(name = "fk_respuesta")
    private Respuesta _respuesta;

    @ManyToOne
    @JoinColumn(name = "fk_opcion")
    private Opcion _opcion;

    public RespuestaOpcion() {

    }

    public RespuestaOpcion(long _id) {
        this._id = _id;
    }

    @Override
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public Respuesta get_respuesta() {
        return _respuesta;
    }

    public void set_respuesta(Respuesta _respuesta) {
        this._respuesta = _respuesta;
    }

    public Opcion get_opcion() {
        return _opcion;
    }

    public void set_opcion(Opcion _opcion) {
        this._opcion = _opcion;
    }

}
