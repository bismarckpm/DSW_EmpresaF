package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "usuario" )
public class Usuario extends EntidadBase
{
    @Id
    @Column( name = "codigo_usuario" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    @Column( name = "nombre")
    private String _nombreUsuario;

    @Column( name = "estado" )
    private String _estado;

    @Column( name = "rol" )
    private String _rol;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "fk_cliente")
    private Cliente _cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "fk_encuestado")
    private Encuestado _encuestado;

    @OneToMany(mappedBy = "_analista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SolicitudEstudio> _solicitudesEstudioAnalista;

    @OneToMany(mappedBy = "_administrador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SolicitudEstudio> _solicitudesEstudioAdministrador;

    @OneToMany(mappedBy = "_cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SolicitudEstudio> _solicitudesEstudioCliente;


  public Usuario(long _id) {
    this._id = _id;
  }

  public Usuario()
    {

    }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_nombreUsuario() {
    return _nombreUsuario;
  }

  public void set_nombreUsuario(String _nombreUsuario) {
    this._nombreUsuario = _nombreUsuario;
  }

  public String get_estado() {
    return _estado;
  }

  public void set_estado(String _estado) {
    this._estado = _estado;
  }

  public String get_rol() {
    return _rol;
  }

  public void set_rol(String _rol) {
    this._rol = _rol;
  }

  public Cliente get_cliente() {
    return _cliente;
  }

  public void set_cliente(Cliente _cliente) {
    this._cliente = _cliente;
  }

  public Encuestado get_encuestado() {
    return _encuestado;
  }

  public void set_encuestado(Encuestado _encuestado) {
    this._encuestado = _encuestado;
  }

  public List<SolicitudEstudio> get_solicitudesEstudioAnalista() {
    return _solicitudesEstudioAnalista;
  }

  public void set_solicitudesEstudioAnalista(List<SolicitudEstudio> _solicitudesEstudioAnalista) {
    this._solicitudesEstudioAnalista = _solicitudesEstudioAnalista;
  }

  public List<SolicitudEstudio> get_solicitudesEstudioAdministrador() {
    return _solicitudesEstudioAdministrador;
  }

  public void set_solicitudesEstudioAdministrador(List<SolicitudEstudio> _solicitudesEstudioAdministrador) {
    this._solicitudesEstudioAdministrador = _solicitudesEstudioAdministrador;
  }

  public List<SolicitudEstudio> get_solicitudesEstudioCliente() {
    return _solicitudesEstudioCliente;
  }

  public void set_solicitudesEstudioCliente(List<SolicitudEstudio> _solicitudesEstudioCliente) {
    this._solicitudesEstudioCliente = _solicitudesEstudioCliente;
  }


}
