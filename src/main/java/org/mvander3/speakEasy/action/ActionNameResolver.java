package org.mvander3.speakEasy.action;

public interface ActionNameResolver<INPUT> {

    public String getActionName(INPUT input);

}
