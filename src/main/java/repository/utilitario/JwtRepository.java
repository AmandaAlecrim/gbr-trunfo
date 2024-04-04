package repository.utilitario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import model.Usuario;
import repository.UsuarioRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Stateless
public class JwtRepository {

    private static final String DEFAULT_SECRET = "64a6988ec0ecacbdf40ecf504e70b9a5f6174a8992c856c7ee22e1e0be03a8890412904b9d17a467d03559fe573c324271615dbcf19le4cfc259b5a01a3bb824";

    @EJB
    UsuarioRepository usuarioRepository;

    public Usuario validarJwt(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(DEFAULT_SECRET.getBytes()))
                    .build()
                    .parseClaimsJws(jwt).getBody();

            Integer id = claims.get("id", Integer.class);
            System.out.println(Keys.hmacShaKeyFor(DEFAULT_SECRET.getBytes()));
            Usuario user = this.usuarioRepository.consultar(id);
            return user;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public String gerarJwtParaUsuario(Usuario user) {

        Date geracao = new Date();
        Calendar expiracao = Calendar.getInstance();
        expiracao.add(Calendar.HOUR, 1);

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(DEFAULT_SECRET.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", "JWT")
                .setExpiration(expiracao.getTime())
                .setIssuedAt(geracao)
                .setId(UUID.randomUUID().toString())
                .claim("type", "Bearer")
                .claim("id", user.getId())
                .claim("login", user.getLogin())
                .claim("nome", user.getNome())
                .compact();
    }
}
