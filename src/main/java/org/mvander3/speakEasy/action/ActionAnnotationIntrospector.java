package org.mvander3.speakEasy.action;

import java.util.Map;

import com.google.common.collect.Maps;

public class ActionAnnotationIntrospector {

    private static final Map<String, ActionDetails> cachedActionDetailsByClass = Maps.newConcurrentMap();

    public ActionDetails getActionDetails(Class<?> classToIntrospect) {
        String className = classToIntrospect.getName();
        ActionDetails actionDetails = cachedActionDetailsByClass.get(className);
        if(actionDetails == null) {
            Action action = classToIntrospect.getAnnotation(Action.class);
            if(action != null) {
                actionDetails = new ActionDetails(action.value(), action.inputClass(), action.outputClass());
                cachedActionDetailsByClass.put(className,  actionDetails);    
            }
        }
        return actionDetails;
    }

}
