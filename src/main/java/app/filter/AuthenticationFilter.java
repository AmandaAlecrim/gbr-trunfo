package app.filter;

import app.security.TokenRequestWrapper;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import model.dto.UserPrincipalDTO;
import repository.utilitario.JwtRepository;

import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/api/*")
public class AuthenticationFilter implements Filter {

    @EJB
    JwtRepository jwtRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        boolean rotaAberta = req.getRequestURI().equals("/gbr-trunfo/api/usuario/login");

        //verifica se está autenticado
        boolean autenticado = false;

        if (!rotaAberta) {
            String jwt = req.getHeader("Authorization");

            if (jwt != null) {
                jwt = jwt.substring(7);
                System.out.println("VALIDANDO: " + jwt);

                UserPrincipalDTO user = this.jwtRepository.validarJwt(jwt);
                if (user != null) {
                    System.out.println("USUARIO do JWT: " + user.getLogin());
                    System.out.println("PERMITINDO ACESSO");
                    autenticado = true;

                    TokenRequestWrapper requestWrapper = new TokenRequestWrapper(req, user);
                    chain.doFilter(requestWrapper, response);
                }
            }
        }

        //se não estiver, retorna não autorizado
        else if (rotaAberta) {
            //continua a execução
            chain.doFilter(request, response);
        } else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
