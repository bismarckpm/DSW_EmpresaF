package ucab.dsw.logica.comando.categoria;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.categoria.MapperCategoria;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;

public class ComandoUpdateCategoria implements ComandoBase {

  private CategoriaDto categoriaDto;
  private long _categoriaId;

  public ComandoUpdateCategoria( long _categoriaId, CategoriaDto categoriaDto) {
    this.categoriaDto = categoriaDto;
    this._categoriaId = _categoriaId;
  }

  public void execute() throws ProblemaExcepcion {

    try{

      DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);
      Categoria categoria = daoCategoria.find(this._categoriaId, Categoria.class);

      Categoria categoriaPorActualizar = MapperCategoria.MapCategoriaDtoToEntityUpdate(this.categoriaDto, categoria);
      Categoria resultado = daoCategoria.update(categoriaPorActualizar);

      this.categoriaDto = MapperCategoria.MapEntityToCategoriaDto(resultado);

    }catch (PersistenceException | DatabaseException ex) {

      throw new ProblemaExcepcion("Esta categoria ya se encuentra agregada en el sistema", ex.getMessage());

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("categoriaId", categoriaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }
  }
}
