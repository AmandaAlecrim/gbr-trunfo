package repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import model.Usuario;
import repository.base.AbstractCrudRepository;

@Stateless
public class UsuarioRepository extends AbstractCrudRepository<Usuario> {

    public Usuario consultarPorLoginSenha(String login, String senha) {

        try {
            Usuario usuario = super.em.createQuery("SELECT u FROM Usuario u WHERE login = :login AND senha = :senha", Usuario.class)
                    .setParameter("login", login)
                    .setParameter("senha", senha)
                    .getSingleResult();
            return usuario;
        } catch (NoResultException noResultException) {
            return null;
        }

    }
}
