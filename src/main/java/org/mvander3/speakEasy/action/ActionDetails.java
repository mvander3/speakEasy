package org.mvander3.speakEasy.action;

public class ActionDetails {
    
    private static final Class<?> NULL_ACTION_CLASS = NullAction.class;
    
    private String actionName;
    private Class<?> inputClass = null;
    private Class<?> outputClass = null;

    public ActionDetails(String actionName, Class<?> inputClass, Class<?> outputClass) {
        this.actionName = actionName;
        if(inputClass == null || inputClass.equals(NULL_ACTION_CLASS)) {
            this.inputClass = null;
        } else {
            this.inputClass = inputClass;
        }
        if(outputClass == null || outputClass.equals(NULL_ACTION_CLASS)) {
            this.outputClass = null;
        } else {
            this.outputClass = outputClass;
        }
    }

    public String getActionName() {
        return this.actionName;
    }
    
    public Class<?> getInputClass() {
        return this.inputClass;
    }
    
    public Class<?> getOutputClass() {
        return this.outputClass;
    }

}
