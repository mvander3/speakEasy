package org.mvander3.speakEasy.message.http;

import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mvander3.speakEasy.message.Message;
import org.mvander3.speakEasy.message.MessageFactory;
import org.mvander3.speakEasy.message.StringMessage;

import com.google.common.collect.Maps;

public class HttpServletRequestMessageFactory implements MessageFactory<HttpServletRequest, String> {

    private Logger log = Logger.getLogger(getClass());

    private boolean addHeadersAsProperties = false;
    private boolean addParametersAsProperties = false;

    @Override
    public Message<String> createMessage(HttpServletRequest input) {
        String content = getContent(input);
        Message<String> message = new StringMessage(content);
        if (addHeadersAsProperties) {
            Map<String, Object> headerMap = getHeaders(input);
            message.addProperties(headerMap);
        }
        if (addParametersAsProperties) {
            Map<String, Object> parameterMap = getParameters(input);
            message.addProperties(parameterMap);
        }
        return message;
    }

    private String getContent(HttpServletRequest input) {
        StringBuffer contentBuffer = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = input.getReader();
            while ((line = reader.readLine()) != null) {
                contentBuffer.append(line);
            }
        } catch (Exception e) {
            log.error("Could not retrieve content from request: ", e);
            throw new RuntimeException(e);
        }
        return contentBuffer.toString();
    }

    private Map<String, Object> getHeaders(HttpServletRequest input) {
        Map<String, Object> headerMap = Maps.newHashMap();
        Enumeration<String> headerNames = input.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Object value = input.getHeader(headerName);
            headerMap.put(headerName, value);
        }
        return headerMap;
    }

    private Map<String, Object> getParameters(HttpServletRequest input) {
        Map<String, Object> parameterMap = Maps.newHashMap();
        Enumeration<String> parameterNames = input.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            Object value = input.getHeader(parameterName);
            parameterMap.put(parameterName, value);
        }
        return parameterMap;
    }

    public void setAddHeadersAsProperties(boolean addHeadersAsProperties) {
        this.addHeadersAsProperties = addHeadersAsProperties;
    }

    public void setAddParametersAsProperties(boolean addParametersAsProperties) {
        this.addParametersAsProperties = addParametersAsProperties;
    }

}
