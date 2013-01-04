package org.mvander3.speakEasy.message;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Required;

public class JsonStringMessageTranscoder implements MessageTranscoder {

    private Logger log = Logger.getLogger(getClass());

    private ObjectMapper jsonMapper;

    @Override
    public <T> T convertFromMessage(Message<?> stringMessage, Class<T> clazz) {
        try {
            return jsonMapper.readValue(stringMessage.getContent().toString(), clazz);
        } catch (IOException e) {
            log.error("Could not deserialize message to class "
                            + clazz.getSimpleName() + ": " + stringMessage.getContent(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public StringMessage convertToMessage(Object input) {
        try {
            String messageContent = jsonMapper.writeValueAsString(input);
            return new StringMessage(messageContent);
        } catch (IOException e) {
            log.error("Could not serialize input: " + input.toString(), e);
            throw new RuntimeException(e);
        }
    }

    @Required
    public void setJsonMapper(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

}
