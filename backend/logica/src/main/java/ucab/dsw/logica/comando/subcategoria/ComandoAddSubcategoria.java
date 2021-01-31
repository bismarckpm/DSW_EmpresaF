package ucab.dsw.logica.comando.subcategoria;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.subcategoria.MapperSubcategoria;

import javax.json.Json;
import javax.json.JsonObject;

public class ComandoAddSubcategoria implements ComandoBase{

  private SubcategoriaDto subcategoriaDto;

  public ComandoAddSubcategoria(SubcategoriaDto subcategoriaDto) {
    this.subcategoriaDto = subcategoriaDto;
  }

  public void execute() {
    try {

      Subcategoria subcategoria = MapperSubcategoria.MapSubcategoriaDtoToEntityAdd(this.subcategoriaDto);

      DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
      Subcategoria resultado = daoSubcategoria.insert(subcategoria);

      this.subcategoriaDto = MapperSubcategoria.MapEntityToSubcategoriaDto(resultado);

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
