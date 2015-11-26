package com.schoolserver.controller;

import com.schoolserver.model.Student;
import com.schoolserver.service.StudentService;
import com.schoolserver.util.JsonTransformer;
import com.schoolserver.util.ResponseError;

import static spark.Spark.*;

public class StudentController {

    private static final int HTTP_BAD_REQUEST = 400;

    public StudentController(StudentService studentService) {

        after((req, res) -> {
            res.type("application/json; charset=utf-8");
        });

        get("/students", (req, res) -> {
            return studentService.getStudents();
        }, new JsonTransformer());

        get("/students/:id", (req, res) -> {
            String id = req.params(":id");
            Student student = studentService.getStudent(id);
            if(student != null) {
                return student;
            }
            res.status(HTTP_BAD_REQUEST);
            return new ResponseError("id '%s' is not found", id);
        }, new JsonTransformer());

//        post("/students", (req, res) -> {
//            String body = req.body();
//            return "";
//        });
    }
}
