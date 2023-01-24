package com.khaled.managment.delegate;

import com.khaled.managment.domain.Department;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author khaled
 */
@ApplicationScoped
public class DepartmentRepository {
    
    @PersistenceContext
    EntityManager em;
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void save(Department department){
        em.persist(department);
    }
    @Transactional(Transactional.TxType.REQUIRED)
    public Department update(Department department){
        return em.merge(department);
    }
    public List<Department> findAll() {
        return em.createNamedQuery(Department.FIND_ALL)
                .getResultList();
    }

    public Optional<Department> findById(Long id) {
        return em.createNamedQuery(Department.FIND_BY_ID)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public Optional<Department> findByName(String name) {
        return em.createNamedQuery(Department.FIND_BY_NAME)
                .setParameter("name", name)
                .getResultStream()
                .findAny();
    }
}
