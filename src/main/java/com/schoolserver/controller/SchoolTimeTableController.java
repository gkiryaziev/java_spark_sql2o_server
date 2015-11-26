package com.schoolserver.controller;

import com.schoolserver.model.SchoolTimeTableDayClass;
import com.schoolserver.service.SchoolTimeTableService;
import com.schoolserver.util.JsonTransformer;
import com.schoolserver.util.ResponseError;

import java.util.List;

import static spark.Spark.*;

public class SchoolTimeTableController {

    private static final int HTTP_BAD_REQUEST = 400;

    public SchoolTimeTableController(SchoolTimeTableService schoolTimeTableService) {
        after((req, res) -> {
            res.type("application/json; charset=utf-8");
        });

        get("/schooltimetable/:day/:class", (req, res) -> {
            String day = req.params(":day");
            String sclass = req.params(":class");
            List<SchoolTimeTableDayClass> list = schoolTimeTableService.getTimeTable(day, sclass);

            if(list.size() > 0) {
                return list;
            }
            res.status(HTTP_BAD_REQUEST);
            return new ResponseError("no time table found", "");


        }, new JsonTransformer());
    }
}
