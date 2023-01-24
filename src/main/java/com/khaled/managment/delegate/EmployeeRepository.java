package com.khaled.managment.delegate;

import com.khaled.managment.domain.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author khaled
 */
@ApplicationScoped
public class EmployeeRepository {

    @PersistenceContext(unitName = "PU")
    EntityManager em;

    @Transactional(Transactional.TxType.REQUIRED)
    public void save(Employee employee) {
        em.persist(employee);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Employee update(Employee employee) {
        return em.merge(employee);
    }

    public List<Employee> findAll() {
        return em.createNamedQuery(Employee.FIND_ALL)
                .getResultList();
    }

    public Optional<Employee> findById(String id) {
        return em.createNamedQuery(Employee.FIND_BY_ID)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public List<Employee> findByFirstName(String firstName) {
        return em.createNamedQuery(Employee.FIND_BY_FIRST_NAME)
                .setParameter("firstName", firstName)
                .getResultList();
    }

    public List<Employee> findByLastName(String lastName) {
        return em.createNamedQuery(Employee.FIND_BY_LAST_NAME)
                .setParameter("firstName", lastName)
                .getResultList();
    }

    public List<Employee> findByDepartment(String department) {
        return em.createNamedQuery(Employee.FIND_BY_LAST_NAME)
                .setParameter("department", department)
                .getResultList();
    }

    public List<Employee> findByFirstNameAndLastNameAndDepratment(
            String firstName,
            String lastName,
            Long depratment
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
        Root<Employee> root = cr.from(Employee.class);
        cr.select(root);
        cr.distinct(true);
        List<Predicate> criteria = new ArrayList<>();
        if (firstName != null) {
            ParameterExpression<String> p
                    = cb.parameter(String.class, "firstName");
            criteria.add(cb.like(root.get("firstName"), p));
        }
        if (lastName != null) {
            ParameterExpression<String> p
                    = cb.parameter(String.class, "lastName");
            criteria.add(cb.like(root.get("lastName"), p));
        }
        if (depratment != null) {
            ParameterExpression<String> p
                    = cb.parameter(String.class, "lastName");
            criteria.add(cb.equal(root.get("lastName"), p));
        }
        cr.where(cb.and(criteria.toArray(new Predicate[0])));
        TypedQuery<Employee> q = em.createQuery(cr);

        if (firstName != null) {
            q.setParameter("firstName", firstName);
        }
        if (lastName != null) {
            q.setParameter("lastName", lastName);
        }
        if (depratment != null) {
            q.setParameter("department", depratment);
        }
        return q.getResultList();
    }
}
