package org.mvander3.speakEasy.message;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

public class Message<CONTENT> {

    private Map<String, Object> properties = Maps.newHashMap();
    private CONTENT content;

    public Message(CONTENT content) {
        this.content = content;
    }

    public CONTENT getContent() {
        return content;
    }

    public String getStringProperty(String name) {
        Object value = properties.get(name.toUpperCase());
        if(value == null) {
           return null;
        }   
        return value.toString();
    }

    public void addProperty(String name, Object value) {
        properties.put(name.toUpperCase(), value);
    }

    public void addProperties(Map<String, Object> propertyMap) {
        for (Entry<String, Object> entry : propertyMap.entrySet()) {
            if(entry.getKey() != null) {
                addProperty(entry.getKey(), entry.getValue());
            }
        }
    }

}
