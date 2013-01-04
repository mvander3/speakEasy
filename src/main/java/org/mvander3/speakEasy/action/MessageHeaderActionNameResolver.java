package org.mvander3.speakEasy.action;

import org.mvander3.speakEasy.message.Message;

public class MessageHeaderActionNameResolver implements ActionNameResolver<Message<?>> {

    private static final String ACTION_HEADER_NAME = "ACTION";

    @Override
    public String getActionName(Message<?> input) {
        String actionName = input.getStringProperty(ACTION_HEADER_NAME);
        if (actionName == null || actionName.isEmpty()) {
            throw new NoActionFoundException();
        }
        return actionName;
    }

}
