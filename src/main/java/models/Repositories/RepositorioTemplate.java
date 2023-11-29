package models.Repositories;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;
public class RepositorioTemplate<T> implements WithSimplePersistenceUnit {
    private Class<T> entityClass;
    public RepositorioTemplate(Class<T> entityClass){
        this.entityClass=entityClass;
    }
    public List<T> buscarTodos() {
        return entityManager().createQuery("from " + this.entityClass.getName()).getResultList();
    }

    public T buscar(Long id) {
        return entityManager().find(entityClass, id);
    }

    public void guardar(T o) {
        EntityTransaction tx = entityManager().getTransaction();
        if(!tx.isActive())
            tx.begin();

        entityManager().persist(o);
        tx.commit();
    }


    public void actualizar(T o) {
        EntityTransaction tx = entityManager().getTransaction();
        if(!tx.isActive())
            tx.begin();

        entityManager().merge(o);
        tx.commit();
    }

    public void eliminar(T o) {
        EntityTransaction tx = entityManager().getTransaction();
        if(!tx.isActive())
            tx.begin();

        entityManager().remove(o);
        tx.commit();
    }
}