package com.schoolserver;

import com.schoolserver.controller.SchoolClassController;
import com.schoolserver.controller.SchoolTimeTableController;
import com.schoolserver.controller.StudentController;
import com.schoolserver.service.SchoolClassService;
import com.schoolserver.service.SchoolTimeTableService;
import com.schoolserver.service.StudentService;
import org.sql2o.Sql2o;

import java.util.Optional;
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        Sql2o sql2o = new Sql2o("jdbc:mysql://192.168.2.11:3306/db_school", "admin", "admin");

        String ipAddress = Optional.ofNullable(System.getenv("OPENSHIFT_DIY_IP")).orElse("0.0.0.0");
        int ipPort = Integer.parseInt(Optional.ofNullable(System.getenv("OPENSHIFT_DIY_PORT")).orElse("8008"));

        ipAddress(ipAddress);
        port(ipPort);

        new StudentController(new StudentService(sql2o));
        new SchoolClassController(new SchoolClassService(sql2o));
        new SchoolTimeTableController(new SchoolTimeTableService(sql2o));
    }
}
