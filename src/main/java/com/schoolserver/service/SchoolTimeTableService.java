package com.schoolserver.service;

import com.schoolserver.model.SchoolTimeTableDayClass;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class SchoolTimeTableService {

    private Sql2o sql2o = null;

    public SchoolTimeTableService(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    // get all lessons by class and day
    public List<SchoolTimeTableDayClass> getTimeTable(String day, String sclass) {
        String sql = "select id,\n" +
                "(select concat_ws('-',\n" +
                "(select begin_time from school_bell where id = school_timetable.bell_id),\n" +
                "(select end_time from school_bell where id = school_timetable.bell_id))) as 'bell',\n" +
                "(select title from school_subject where id = school_timetable.subject_id) as 'subject'\n" +
                "from school_timetable where \n" +
                "day_id = (select id from school_day where title = :day) and \n" +
                "class_id = (select id from school_class where title = :class);";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("day", day)
                    .addParameter("class", sclass)
                    .executeAndFetch(SchoolTimeTableDayClass.class);
        }
    }
}
