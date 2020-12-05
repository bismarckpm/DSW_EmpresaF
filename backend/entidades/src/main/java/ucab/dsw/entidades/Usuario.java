package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "usuario" )
public class Usuario
{
    @Id
    @Column( name = "codigo_usuario" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    @Column( name = "nombre")
    private String _nombreUsuario;

    @Column( name = "estado" )
    private String _estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "fk_cliente")
    private Cliente _cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "fk_encuestado")
    private Encuestado _encuestado;

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
}
