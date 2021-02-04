package ucab.dsw.logica.comando.categoria;

import org.eclipse.persistence.exceptions.DatabaseException;
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

public class ComandoAddCategoria implements ComandoBase {

  private CategoriaDto categoriaDto;

  public ComandoAddCategoria(CategoriaDto categoriaDto) {
    this.categoriaDto = categoriaDto;
  }

  public void execute() throws ProblemaExcepcion {

    try{

      Categoria categoria = MapperCategoria.MapCategoriaDtoToEntityAdd(this.categoriaDto);

      DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);
      Categoria resultado = daoCategoria.insert(categoria);

      this.categoriaDto = MapperCategoria.MapEntityToCategoriaDto(resultado);

    }
    catch (PersistenceException | DatabaseException ex) {

      throw new ProblemaExcepcion("Esta categoria ya se encuentra agregada en el sistema", ex.getMessage());

    }
    catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

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
