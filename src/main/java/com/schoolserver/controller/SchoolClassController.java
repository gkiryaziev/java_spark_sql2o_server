package com.schoolserver.controller;

import com.schoolserver.model.SchoolClass;
import com.schoolserver.service.SchoolClassService;
import com.schoolserver.util.JsonTransformer;
import com.schoolserver.util.ResponseError;
import static spark.Spark.*;

public class SchoolClassController {

    private static final int HTTP_BAD_REQUEST = 400;

    public SchoolClassController(SchoolClassService schoolClassService) {
        after((req, res) -> {
            res.type("application/json; charset=utf-8");
        });

        get("/schoolclass", (req, res) -> {
            return schoolClassService.getSchoolClasses();
        }, new JsonTransformer());

        get("/schoolclass/:id", (req, res) -> {
            String id = req.params(":id");
            SchoolClass schoolClass = schoolClassService.getSchoolClass(id);
            if(schoolClass != null) {
                return schoolClass;
            }
            res.status(HTTP_BAD_REQUEST);
            return new ResponseError("id '%s' is not found", id);
        }, new JsonTransformer());
    }
}
