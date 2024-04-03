package service;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Carta;
import repository.CartaRepository;

import java.util.List;

@Path("/carta")
public class CartaService {

    @EJB
    CartaRepository cartaRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Integer inserir(Carta carta) {
        return this.cartaRepository.inserir(carta);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Carta consultar( @PathParam("id") Integer id) {
        return this.cartaRepository.consultar(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void atualizar(Carta carta) {
        this.cartaRepository.atualizar(carta);
    }

    @DELETE
    @Path("/{id}")
    public void remover( @PathParam("id") Integer id) {
        this.cartaRepository.remover(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Carta> pesquisar() {
        return this.cartaRepository.pesquisar();
    }
}
