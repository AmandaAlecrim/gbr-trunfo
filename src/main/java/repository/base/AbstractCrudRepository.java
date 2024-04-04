package repository.base;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.lang.reflect.ParameterizedType;

public class AbstractCrudRepository<T> {

    protected Class<T> persistentClass;

    @PersistenceContext(unitName = "primary")
    protected EntityManager em;

    @PostConstruct
    public void init() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }


    public void inserir(T entity) {
        this.em.persist(entity);
    }

    public T consultar(Integer id) {
        return this.em.find(this.persistentClass, id);
    }

    public void atualizar(T entity) {
        em.merge(entity);
    }

    public void remover(Integer id) {
        T entity = this.consultar(id);
        if (entity != null) {
            this.em.remove(entity);
        }
    }
}
