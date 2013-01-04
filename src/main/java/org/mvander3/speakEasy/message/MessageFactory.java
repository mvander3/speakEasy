package org.mvander3.speakEasy.message;

public interface MessageFactory<INPUT, OUTPUT> {

    public Message<OUTPUT> createMessage(INPUT input);

}
