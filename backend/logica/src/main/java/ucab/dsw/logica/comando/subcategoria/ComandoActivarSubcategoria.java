package ucab.dsw.logica.comando.subcategoria;

import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.subcategoria.MapperSubcategoria;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class ComandoActivarSubcategoria implements ComandoBase{

  private  long idSubcategoria;
  private  SubcategoriaDto subcategoriaDto;

  public ComandoActivarSubcategoria(long idSubcategoria){ this.idSubcategoria = idSubcategoria;}

  public  void execute(){

    try{

      DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
      Subcategoria subcategoria = daoSubcategoria.find(this.idSubcategoria, Subcategoria.class);

      DaoMarca daoMarca = Fabrica.crear(DaoMarca.class);
      List<Marca> marcas = daoMarca.findAll(Marca.class);

      subcategoria.set_estado("activo");

      for (Marca marca:marcas){

        if(marca.get_subcategoria().get_id() == subcategoria.get_id()){
          marca.set_estado("activo");
          daoMarca.update(marca);

        }
      }

      Subcategoria resultado = daoSubcategoria.update(subcategoria);
      this.subcategoriaDto = MapperSubcategoria.MapEntityToSubcategoriaDto(resultado);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

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
