package org.mvander3.speakEasy.request;

import org.apache.log4j.Logger;
import org.mvander3.speakEasy.action.ActionAnnotationIntrospector;
import org.mvander3.speakEasy.action.ActionDetails;
import org.mvander3.speakEasy.message.Message;
import org.mvander3.speakEasy.message.MessageTranscoder;
import org.mvander3.speakEasy.message.RequestHandler;
import org.springframework.beans.factory.annotation.Required;

public abstract class AbstractTypedRequestHandler<INPUT, OUTPUT> implements RequestHandler<String, String> {

    private Logger log = Logger.getLogger(getClass());
    
    private MessageTranscoder messageTranscoder;
    
    private static final ActionAnnotationIntrospector actionAnnotationIntrospector = new ActionAnnotationIntrospector();
    
    private ActionDetails actionDetails = null;
    
    public void init() {
        log.info("Starting request handler of type: " + getClass());
        actionDetails = actionAnnotationIntrospector.getActionDetails(this.getClass());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Message<String> processRequestMessage(Message<String> requestMessage) {
        INPUT input = (INPUT) messageTranscoder.convertFromMessage(requestMessage, actionDetails.getInputClass());
        OUTPUT output = processRequestMessage(input);
        return (Message<String>)messageTranscoder.convertToMessage(output);
    }

    protected abstract OUTPUT processRequestMessage(INPUT input);

    @Required
    public void setMessageTranscoder(MessageTranscoder messageTranscoder) {
        this.messageTranscoder = messageTranscoder;
    }

}
