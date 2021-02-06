package ucab.dsw.logica.comando.subcategoria;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.subcategoria.MapperSubcategoria;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;

public class ComandoUpdateSubcategoria implements ComandoBase {

  private  SubcategoriaDto subcategoriaDto;
  private long subcategoriaId;

  public ComandoUpdateSubcategoria(long subcategoriaId,SubcategoriaDto subcategoriaDto){
    this.subcategoriaDto = subcategoriaDto;
    this.subcategoriaId = subcategoriaId;
  }

  public  void execute() throws Exception {

    try{

      DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
      Subcategoria subcategoria = daoSubcategoria.find(this.subcategoriaId, Subcategoria.class);

      Subcategoria subcategoriaPorActualizar = MapperSubcategoria.MapSubcategoriaDtoToEntityUpdate(this.subcategoriaDto, subcategoria);
      Subcategoria resultado = daoSubcategoria.update(subcategoriaPorActualizar);

      this.subcategoriaDto = MapperSubcategoria.MapEntityToSubcategoriaDto(resultado);

    }catch (PersistenceException | DatabaseException ex){

      throw new ProblemaExcepcion("Esta subcategoria ya se encuentra agregada en el sistema",ex.getMessage());

    } catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("subcategoria", subcategoriaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }


}
