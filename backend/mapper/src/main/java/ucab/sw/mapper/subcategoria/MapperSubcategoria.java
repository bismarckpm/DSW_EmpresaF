package ucab.sw.mapper.subcategoria;


import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;

public class MapperSubcategoria {
  public static Subcategoria MapSubcategoriaDtoToEntityAdd(SubcategoriaDto subcategoriaDto){

    try {

      Subcategoria subcategoria = new Subcategoria();
      subcategoria.set_nombreSubcategoria(subcategoriaDto.getNombreSubcategoria());
      subcategoria.set_estado("activo");
      DaoCategoria daoCategoria = new DaoCategoria();
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
      SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
      subcategoriaDto.setId(subcategoria.get_id());
      subcategoriaDto.setNombreSubcategoria(subcategoria.get_nombreSubcategoria());
      subcategoriaDto.setEstado(subcategoria.get_estado());
      CategoriaDto categoriaDto = new CategoriaDto();
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

}
