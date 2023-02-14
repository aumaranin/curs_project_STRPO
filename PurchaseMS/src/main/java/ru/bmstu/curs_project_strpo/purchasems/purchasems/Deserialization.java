package ru.bmstu.curs_project_strpo.purchasems.purchasems;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Deserialization
{
    public static Map<String, Object> deserializeJson(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef
                = new TypeReference<HashMap<String, Object>>() {};
        Map<String, Object> result;
        try
        {
            result = mapper.readValue(json, typeRef);
        } catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
        return result;
    }
}
