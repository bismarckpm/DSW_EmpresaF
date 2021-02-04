package ucab.dsw.logica.fabrica;

import ucab.dsw.dtos.DtoBase;
import java.lang.reflect.InvocationTargetException;

public class Fabrica<T> {

  private Class<T> tipo;

  public Fabrica(Class<T> tipo) {

    this.tipo= tipo;
  }


  public T getInstancia() {
    try {
      return tipo.newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T crear(Class<T> tipo) {
    return new Fabrica<T>(tipo).getInstancia();
  }

  public static <T> T crearComandoConDto(Class<T> tipo, DtoBase parametro) throws  IllegalAccessException, InvocationTargetException, InstantiationException {
    return (T) tipo.getConstructors()[0].newInstance(parametro);
  }

  public static <T> T crearComandoConId(Class<T> tipo, long parametro) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return (T) tipo.getConstructors()[0].newInstance(parametro);
  }

  public static <T> T crearComandoConAmbos(Class<T> tipo, long _id, DtoBase parametro) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return (T) tipo.getConstructors()[0].newInstance(_id,parametro);
  }

  public static <T> T crearComandoSeguridad(Class<T> tipo, String parametro) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return (T) tipo.getConstructors()[0].newInstance(parametro);
  }


}
