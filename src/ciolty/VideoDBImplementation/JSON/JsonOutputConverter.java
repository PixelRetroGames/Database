package ciolty.VideoDBImplementation.JSON;

import ciolty.engine.JSON.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonOutputConverter implements JsonConverter {
    /**
     * @param object
     * @return Json format string of object
     * @throws JsonProcessingException
     */
    public String convert(final Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
