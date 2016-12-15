package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JavaServiceFacade {
    private final EntityManager em;

    public JavaServiceFacade() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Model-1");
        em = emf.createEntityManager();
    }

    /**
     * All changes that have been made to the managed entities in the
     * persistence context are applied to the database and committed.
     */
    public void commitTransaction() {
        final EntityTransaction entityTransaction = em.getTransaction();
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }
        entityTransaction.commit();
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public <T> T persistEntity(T entity) {
        em.persist(entity);
        commitTransaction();
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        entity = em.merge(entity);
        commitTransaction();
        return entity;
    }

    public void removeDepartment(Department department) {
        department = em.find(Department.class, department.getDepartmentId());
        em.remove(department);
        commitTransaction();
    }

    /** <code>select o from Department o</code> */
    public List<Department> getDepartmentFindAll() {
        return em.createNamedQuery("Department.findAll", Department.class).getResultList();
    }

    public void removeEmployee(Employee employee) {
        employee = em.find(Employee.class, employee.getEmployeeId());
        em.remove(employee);
        commitTransaction();
    }

    /** <code>select o from Employee o</code> */
    public List<Employee> getEmployeeFindAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }
}
