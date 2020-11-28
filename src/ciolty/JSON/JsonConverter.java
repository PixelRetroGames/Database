package ciolty.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonConverter {
    /**
     * @param object
     * @return Json string format of object
     * @throws JsonProcessingException
     */
    String convert(Object object) throws JsonProcessingException;
}
