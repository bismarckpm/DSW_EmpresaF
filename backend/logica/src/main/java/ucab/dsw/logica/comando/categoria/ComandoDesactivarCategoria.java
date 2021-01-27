package ucab.dsw.logica.comando.categoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.categoria.MapperCategoria;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class ComandoDesactivarCategoria implements ComandoBase {

  private long idCategoria;
  private CategoriaDto categoriaDto;

  public ComandoDesactivarCategoria(long idCategoria) {
    this.idCategoria = idCategoria;
  }

  public void execute() {

    try{

      DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);
      Categoria categoria = daoCategoria.find(this.idCategoria, Categoria.class);

      DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
      List<Subcategoria> subcategorias = daoSubcategoria.findAll(Subcategoria.class);

      DaoMarca daoMarca = Fabrica.crear(DaoMarca.class);
      List<Marca> marcas = daoMarca.findAll(Marca.class);

      categoria.set_estado("inactivo");

      for(Subcategoria subcategoria:subcategorias){

        if(subcategoria.get_categoria().get_id() == this.idCategoria){
          subcategoria.set_estado("inactivo");
          daoSubcategoria.update(subcategoria);

          for (Marca marca:marcas){

            if(marca.get_subcategoria().get_id() == subcategoria.get_id()){
              marca.set_estado("inactivo");
              daoMarca.update(marca);

            }

          }

        }

      }

      Categoria resultado = daoCategoria.update(categoria);
      this.categoriaDto = MapperCategoria.MapEntityToCategoriaDto(resultado);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("categoria", this.categoriaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }
  }
}
