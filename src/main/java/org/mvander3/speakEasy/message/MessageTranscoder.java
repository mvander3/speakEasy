package org.mvander3.speakEasy.message;

public interface MessageTranscoder {

    public <T> T convertFromMessage(Message<?> stringMessage, Class<T> clazz);

    public Message<?> convertToMessage(Object typedMessage);

}
