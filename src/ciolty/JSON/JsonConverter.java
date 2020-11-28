package ciolty.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public interface JsonConverter {
    public String convert(Object object) throws JsonProcessingException;
}
