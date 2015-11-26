package com.schoolserver.service;

import com.schoolserver.model.SchoolClass;
import com.schoolserver.model.Student;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class SchoolClassService {

    private Sql2o sql2o = null;

    public SchoolClassService(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    // get all school classes
    public List<SchoolClass> getSchoolClasses() {
        String sql = "select * from school_class";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql).executeAndFetch(SchoolClass.class);
        }
    }

    // get school class by id
    public SchoolClass getSchoolClass(String id) {
        String sql = "select * from school_class where id = :id";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql).addParameter("id", id).executeAndFetchFirst(SchoolClass.class);
        }
    }
}
