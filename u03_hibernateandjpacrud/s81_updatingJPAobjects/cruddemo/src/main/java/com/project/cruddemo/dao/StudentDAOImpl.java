package com.project.cruddemo.dao;

import com.project.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // Define field for entity manager
    private EntityManager entityManager;

    // Inject entity manager using constructor injection
    @Autowired // Optional if we have only one constructor like we have here
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Implement save method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {

        // Create query
        // TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);

        // Or maybe we can order it by last name
        // 'asc' is for ascending, 'desc' is for descending
        TypedQuery<Student> query = entityManager.createQuery("FROM Student order by lastName asc", Student.class);

        // Return query results
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {

        // Create query
        TypedQuery<Student> query
                = entityManager.createQuery("FROM Student where lastName = :lastName", Student.class);

        // Set query parameters
        query.setParameter("lastName", lastName);

        // Return query results
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }
}
