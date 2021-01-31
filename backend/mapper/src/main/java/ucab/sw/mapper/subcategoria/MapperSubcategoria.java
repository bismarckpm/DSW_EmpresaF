package ucab.sw.mapper.subcategoria;


import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.logica.fabrica.Fabrica;

public class MapperSubcategoria {
  public static Subcategoria MapSubcategoriaDtoToEntityAdd(SubcategoriaDto subcategoriaDto){

    try {

      Subcategoria subcategoria = Fabrica.crear(Subcategoria.class);
      subcategoria.set_nombreSubcategoria(subcategoriaDto.getNombreSubcategoria());
      subcategoria.set_estado("activo");
      DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);
      Categoria categoria = daoCategoria.find(subcategoriaDto.getCategoria().getId(), Categoria.class);
      subcategoria.set_categoria(categoria);

      return subcategoria;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Subcategoria MapSubcategoriaDtoToEntityUpdate(SubcategoriaDto subcategoriaDto, Subcategoria subcategoriaPorActualizar){

    try {

      subcategoriaPorActualizar.set_nombreSubcategoria(subcategoriaDto.getNombreSubcategoria());

      return subcategoriaPorActualizar;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }


  public static SubcategoriaDto MapEntityToSubcategoriaDto(Subcategoria subcategoria){

    try{
      SubcategoriaDto subcategoriaDto = Fabrica.crear(SubcategoriaDto.class);
      subcategoriaDto.setId(subcategoria.get_id());
      subcategoriaDto.setNombreSubcategoria(subcategoria.get_nombreSubcategoria());
      subcategoriaDto.setEstado(subcategoria.get_estado());
      CategoriaDto categoriaDto = Fabrica.crear(CategoriaDto.class);
      categoriaDto.setNombreCategoria(subcategoria.get_categoria().get_nombreCategoria());
      categoriaDto.setId(subcategoria.get_categoria().get_id());
      categoriaDto.setEstado(subcategoria.get_categoria().get_estado());
      subcategoriaDto.setCategoria(categoriaDto);

      return subcategoriaDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Subcategoria MapSubcategoriaDtoToEntityDisabled(Subcategoria subcategoria){

    try{

      Subcategoria sub = Fabrica.crear(Subcategoria.class);
      subcategoria.set_estado("inactivo");

      return sub;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Subcategoria MapSubcategoriaDtoToEntityEnabled(Subcategoria subcategoria){

    try{

      Subcategoria sub = Fabrica.crear(Subcategoria.class);
      subcategoria.set_estado("activo");

      return sub;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }


}
