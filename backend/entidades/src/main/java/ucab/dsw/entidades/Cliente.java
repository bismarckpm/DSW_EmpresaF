package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente
{
  @Column(name = "nombre")
  private String _nombre;

  @Id
  @Column( name = "codigo_cliente" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;


  public Cliente(long _id) {
    this._id = _id;
  }

  public Cliente() {

  }


  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String getNombre() {
    return _nombre;
  }

  public void setNombre(String nombre) {
    this._nombre = nombre;
  }


}
