package com.schoolserver.service;

import com.schoolserver.model.Student;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    private Sql2o sql2o = null;

    public StudentService(Sql2o sql2o) {
        this.sql2o = sql2o;

        // column mapping
        Map<String, String> colMaps = new HashMap<String,String>();
        colMaps.put("first_name", "firstName");
        colMaps.put("last_name", "lastName");
        colMaps.put("middle_name", "middleName");
        colMaps.put("class_id", "classId");
        this.sql2o.setDefaultColumnMappings(colMaps);
    }

    // get all students
    public List<Student> getStudents() {
        String sql = "select * from student";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql).executeAndFetch(Student.class);
        }
    }

    // get student by id
    public Student getStudent(String id) {
        String sql = "select * from student where id = :id";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Student.class);
        }
    }
}
