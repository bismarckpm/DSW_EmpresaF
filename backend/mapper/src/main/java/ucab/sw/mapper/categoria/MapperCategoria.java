package ucab.sw.mapper.categoria;

import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.logica.fabrica.Fabrica;

public class MapperCategoria {

  public static Categoria MapCategoriaDtoToEntityAdd(CategoriaDto categoriaDto){

    try {

      Categoria categoria = Fabrica.crear(Categoria.class);
      categoria.set_nombreCategoria(categoriaDto.getNombreCategoria());
      categoria.set_estado("activo");

      return categoria;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Categoria MapCategoriaDtoToEntityUpdate(CategoriaDto categoriaDto, Categoria categoriaPorActualizar){

    try {

      categoriaPorActualizar.set_nombreCategoria(categoriaDto.getNombreCategoria());

      return categoriaPorActualizar;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }


  public static CategoriaDto MapEntityToCategoriaDto(Categoria categoria){

    try{

      CategoriaDto categoriaDto = Fabrica.crear(CategoriaDto.class);
      categoriaDto.setId(categoria.get_id());
      categoriaDto.setNombreCategoria(categoria.get_nombreCategoria());
      categoriaDto.setEstado(categoria.get_estado());

      return categoriaDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Categoria MapCategoriaDtoToEntityDisabled(Categoria categoria){

    try{

      Categoria cat = Fabrica.crear(Categoria.class);
      categoria.set_estado("inactivo");

      return cat;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Categoria MapCategoriaDtoToEntityEnabled(Categoria categoria){

    try{

      Categoria cat = Fabrica.crear(Categoria.class);
      categoria.set_estado("activo");

      return cat;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }



}
