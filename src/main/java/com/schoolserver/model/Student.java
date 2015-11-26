package com.schoolserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Student {
    public int id;
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("last_name")
    public String lastName;
    @JsonProperty("middle_name")
    public String middleName;
    public Date dob;
    @JsonProperty("class_id")
    public int classId;
}
