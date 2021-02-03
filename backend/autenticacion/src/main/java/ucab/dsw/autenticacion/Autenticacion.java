package ucab.dsw.autenticacion;

import io.jsonwebtoken.*;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.directorioactivo.DirectorioActivo;

import javax.crypto.spec.SecretKeySpec;
import javax.json.Json;
import javax.json.JsonObject;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

/**
 * Clase para gestionar los metodos de JWT
 *
 */
public class Autenticacion {
  private String secret = "Clavesecreta02847585923hsddkwn";

  private Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
    SignatureAlgorithm.HS256.getJcaName());


  public Autenticacion() {
  }

  /**
   * Metodo para autenticar un usuario
   *
   *
   * @param usuarioDto DTO del usuario
   * @return Boolean success: True; error: False
   * code}
   */
  public static Boolean isAuthenticated(UsuarioDto usuarioDto){
    DirectorioActivo directorioActivo = new DirectorioActivo();
    return directorioActivo.userAuthentication(usuarioDto);
  }

  /**
   * Metodo para generar el token de un usuario al ser autenticado
   *
   *
   * @param usuarioDto DTO del usuario
   * @return String success: jwtToken
   * code}
   */
  public String generateToken(UsuarioDto usuarioDto){
    String jwtToken = null;
    DirectorioActivo directorio = new DirectorioActivo();

    if(isAuthenticated(usuarioDto)){
      Instant now = Instant.now();
      jwtToken = Jwts.builder()
        .claim("nombreUsuario", usuarioDto.getNombreUsuario())
        .claim("rol", directorio.getEntry(usuarioDto))
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plus(10800l, ChronoUnit.MINUTES)))
        .signWith(SignatureAlgorithm.HS256, hmacKey)
        .compact();
    }

    return  jwtToken;
  }

  /**
   * Metodo para decodificar el token de un usuario autenticado
   *
   *
   * @param token token del usuario autenticado
   * @return Claims success: jwt
   * code}
   */
  public Claims decode(String token){

    String secret = "Clavesecreta02847585923hsddkwn";
    Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
      SignatureAlgorithm.HS256.getJcaName());

    Claims jwt = Jwts.parser().setSigningKey(hmacKey).parseClaimsJws(token).getBody();

    return jwt;

  }

}
