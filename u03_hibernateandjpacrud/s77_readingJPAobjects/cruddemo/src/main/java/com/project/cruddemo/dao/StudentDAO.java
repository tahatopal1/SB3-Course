package com.project.cruddemo.dao;

import com.project.cruddemo.entity.Student;

public interface StudentDAO {

    void save(Student student);

    Student findById(int id);

}
