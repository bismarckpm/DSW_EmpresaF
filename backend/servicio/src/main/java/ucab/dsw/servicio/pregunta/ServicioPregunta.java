package ucab.dsw.servicio.pregunta;


import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.logica.comando.autenticacion.ComandoDecode;
import ucab.dsw.logica.comando.pregunta.ComandoAddPregunta;
import ucab.dsw.logica.comando.pregunta.ComandoGetPreguntas;
import ucab.dsw.logica.comando.pregunta.ComandoGetPreguntasSugeridas;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase para gestionar las preguntas
 *
 */
@Path("/preguntas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioPregunta extends AplicacionBase {

    /**
     * Metodo para agregar una pregunta Accedido mediante /preguntas/ con el
     * metodo POST
     *
     * @param preguntaDto DTO de la pregunta
     * @return JSON success: {pregunta, estado, code}; error: {mensaje, estado,
     * code}
     */
    @POST
    @Path("/")
    public Response addPregunta(@HeaderParam("authorization") String token, PreguntaDto preguntaDto) {

        try {

          ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
          comandoDecode.execute();

          ComandoAddPregunta comandoAddPregunta = Fabrica.crearComandoConDto(ComandoAddPregunta.class, preguntaDto);
          comandoAddPregunta.execute();

          return Response.ok().entity(comandoAddPregunta.getResultado()).build();

        } catch (ProblemaExcepcion ex) {

          ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

          return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMensaje_soporte(), ex.getMensaje(), "error", 400)).build();

        } catch (Exception ex) {

          String mensaje = "Ha ocurrido un error en el servidor";
          ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

          return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

        }

    }

    /**
     * Metodo para Obtener todas las preguntas Accedido. mediante /preguntas/
     * con el metodo GET
     *
     * @return JSON success: {code, estado, preguntas}; error: {mensaje, estado,
     * code}
     *
     */
    @GET
    @Path("/")
    public Response getPreguntas(@HeaderParam("authorization") String token) {

        try {

          ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
          comandoDecode.execute();

          ComandoGetPreguntas comandoGetPreguntas = Fabrica.crear(ComandoGetPreguntas.class);
          comandoGetPreguntas.execute();

          return  Response.ok().entity(comandoGetPreguntas.getResultado()).build();

        } catch (Exception ex) {

          String mensaje = "Ha ocurrido un error en el servidor";
          ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

          return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

        }

    }

    /**
     * Metodo para Obtener una pregunta especifica. Accedido mediante
     * /preguntas/{id} con el metodo GET
     *
     * @param _id identificador de la pregunta solicitada
     * @return JSON success: {id, descripcion, tipo, min, max, estado, code};
     * error: {mensaje, estado, code}
     *
     */
    /*@GET
    @Path("/{id}")
    public Response getPregunta(@PathParam("id") long _id) {

        Pregunta pregunta = null;
        JsonObject data;

        try {
            DaoPregunta dao = new DaoPregunta();
            pregunta = dao.find(_id, Pregunta.class);

            JsonObject JsonPregunta = pregunta.toJson();

            data = Json.createObjectBuilder()
                    .add("data", JsonPregunta)
                    .add("estado", "success")
                    .add("code", 200)
                    .build();
        } catch (Exception ex) {

            data = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();
            return Response.ok().entity(data).build();
        }
        return Response.ok().entity(data).build();
    }*/

  /**
   * Metodo para Obtener las preguntas sugeridas para una encuesta. Accedido mediante
   * /preguntas/{idEncuesta}/sugerencias con el metodo GET
   *
   * @param _idSolicitud identificador de la solicitud
   * @return JSON success: {preguntas, estado, code};
   * error: {mensaje, estado, code}
   *
   */
    @GET
    @Path("/{idSolicitud}/sugerencias")
    public Response getPreguntasSugeridas(@HeaderParam("authorization") String token, @PathParam("idSolicitud") long _idSolicitud){

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoGetPreguntasSugeridas comandoGetPreguntasSugeridas = Fabrica.crearComandoConId(ComandoGetPreguntasSugeridas.class, _idSolicitud);
      comandoGetPreguntasSugeridas.execute();

      return Response.ok().entity(comandoGetPreguntasSugeridas.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

}
