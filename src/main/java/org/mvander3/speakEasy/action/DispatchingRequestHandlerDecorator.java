package org.mvander3.speakEasy.action;

import org.mvander3.speakEasy.message.Message;
import org.mvander3.speakEasy.message.RequestHandler;
import org.springframework.beans.factory.annotation.Required;

public class DispatchingRequestHandlerDecorator<INPUT, OUTPUT> implements RequestHandler<INPUT, OUTPUT> {

    private RequestHandlerRegistry requestHandlerRegistry;
    private ActionNameResolver<Message<?>> actionNameResolver;

    @Override
    @SuppressWarnings("unchecked")
    public Message<OUTPUT> processRequestMessage(Message<INPUT> requestMessage) {
        String actionName = this.actionNameResolver.getActionName(requestMessage);
        RequestHandler<INPUT, OUTPUT> requestHandler = (RequestHandler<INPUT, OUTPUT>) this.requestHandlerRegistry.getRequestHandler(actionName);
        if (requestHandler == null) {
            throw new RuntimeException("No request handler registered for action " + actionName);
        }
        return requestHandler.processRequestMessage(requestMessage);
    }

    @Required
    public void setRequestHandlerRegistry(RequestHandlerRegistry requestHandlerRegistry) {
        this.requestHandlerRegistry = requestHandlerRegistry;
    }

    @Required
    public void setActionNameResolver(ActionNameResolver<Message<?>> actionNameResolver) {
        this.actionNameResolver = actionNameResolver;
    }
}
