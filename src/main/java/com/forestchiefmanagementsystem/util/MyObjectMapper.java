package com.forestchiefmanagementsystem.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyObjectMapper extends ObjectMapper{
    public static ObjectMapper getObjerctMapper(){
        return new ObjectMapper();
    }
    public static String myWriteValueAsString(Object obj) throws JsonProcessingException {
        return getObjerctMapper().writeValueAsString(obj);
    }
}
