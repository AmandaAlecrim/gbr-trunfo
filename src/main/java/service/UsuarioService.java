package service;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Usuario;
import model.dto.LoginDTO;
import model.dto.LoginResponseDTO;
import repository.UsuarioRepository;
import repository.utilitario.JwtRepository;

@Path("/usuario")
public class UsuarioService {

    @EJB
    UsuarioRepository usuarioRepository;

    @EJB
    JwtRepository jwtRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Usuario consultar(@PathParam("id") Integer id) {
        return this.usuarioRepository.consultar(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public LoginResponseDTO login(LoginDTO dto) {
        Usuario user = this.usuarioRepository.consultarPorLoginSenha(dto.getLogin(), dto.getSenha());
        if (user != null) {
            LoginResponseDTO response = new LoginResponseDTO();
            response.setAcessToken(this.jwtRepository.gerarJwtParaUsuario(user));
            return response;
        } else {
            return null;
        }
    }
}
