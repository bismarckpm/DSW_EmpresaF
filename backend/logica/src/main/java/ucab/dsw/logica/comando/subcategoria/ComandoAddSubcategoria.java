package ucab.dsw.logica.comando.subcategoria;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.subcategoria.MapperSubcategoria;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;

public class ComandoAddSubcategoria implements ComandoBase{

  private SubcategoriaDto subcategoriaDto;

  public ComandoAddSubcategoria(SubcategoriaDto subcategoriaDto) {
    this.subcategoriaDto = subcategoriaDto;
  }

  public void execute() throws ProblemaExcepcion {

    try {

      Subcategoria subcategoria = MapperSubcategoria.MapSubcategoriaDtoToEntityAdd(this.subcategoriaDto);

      DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
      Subcategoria resultado = daoSubcategoria.insert(subcategoria);

      this.subcategoriaDto = MapperSubcategoria.MapEntityToSubcategoriaDto(resultado);

    }catch (PersistenceException | DatabaseException ex){

      throw new ProblemaExcepcion("Esta subcategoria ya se encuentra agregada en el sistema",ex.getMessage());

    } catch (Exception ex) {
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("subcategoria", this.subcategoriaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
