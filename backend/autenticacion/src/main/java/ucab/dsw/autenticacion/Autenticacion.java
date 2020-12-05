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

public class Autenticacion {
  private String secret = "Clavesecreta02847585923hsddkwn";

  private Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
    SignatureAlgorithm.HS256.getJcaName());

  private JsonObject data;

  public Autenticacion() {
  }

  public static Boolean isAuthenticated(UsuarioDto usuarioDto){
    DirectorioActivo directorioActivo = new DirectorioActivo();
    return directorioActivo.userAuthentication(usuarioDto);
  }

  public String generateToken(UsuarioDto usuarioDto){
    String jwtToken = null;

    if(isAuthenticated(usuarioDto)){
      Instant now = Instant.now();
      jwtToken = Jwts.builder()
        .claim("nombreUsuario", usuarioDto.getNombreUsuario())
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
        .signWith(SignatureAlgorithm.HS256, hmacKey)
        .compact();
    }

    return  jwtToken;
  }

  public Claims decode(String token){
    String secret = "Clavesecreta02847585923hsddkwn";
    Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
      SignatureAlgorithm.HS256.getJcaName());

    Claims jwt = Jwts.parser().setSigningKey(hmacKey).parseClaimsJws(token).getBody();


    return jwt;
  }

}
