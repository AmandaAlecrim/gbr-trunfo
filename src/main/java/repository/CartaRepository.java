package repository;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Carta;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Stateless
public class CartaRepository {

    @PersistenceContext
    private EntityManager em;

    public Integer inserir(Carta c) {
        c.setDataCadastro(LocalDateTime.now());
        this.em.persist(c);
        return c.getId();
    }

    public Carta consultar(Integer id) {
        return this.em.find(Carta.class, id);
    }

    public void atualizar(Carta c) {
        c.setDataAtualizacao(LocalDateTime.now());
        em.merge(c);
    }

    public void remover(Integer id) {
        Carta c = this.consultar(id);
        if (c != null) {
            this.em.remove(c);
        }
    }

    public List<Carta> pesquisar() {
        return this.em.createQuery("SELECT c FROM Carta c", Carta.class)
                .getResultList();
    }
}
