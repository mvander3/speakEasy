package org.mvander3.speakEasy.message;

public interface RequestHandler<INPUT, OUTPUT> {

    public Message<OUTPUT> processRequestMessage(Message<INPUT> requestMessage);

}
