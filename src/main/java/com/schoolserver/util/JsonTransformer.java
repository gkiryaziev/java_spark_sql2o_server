package com.schoolserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String render(Object o) throws Exception {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(o);
    }
}
