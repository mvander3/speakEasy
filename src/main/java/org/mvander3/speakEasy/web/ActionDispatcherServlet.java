package org.mvander3.speakEasy.web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mvander3.speakEasy.message.Message;
import org.mvander3.speakEasy.message.MessageFactory;
import org.mvander3.speakEasy.message.RequestHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ActionDispatcherServlet extends HttpServlet implements ApplicationContextAware {

    private static final long serialVersionUID = 1076865579334159710L;
    
    private static final String MESSAGE_FACTORY_BEAN_NAME = "messageFactory";
    private static final String REQUEST_HANDLER_BEAN_NAME = "dispatchingRequestHandler";
    
    private Logger log = Logger.getLogger(getClass());

    private ApplicationContext applicationContext;
    
    private RequestHandler<String, String> requestHandler;
    private MessageFactory<HttpServletRequest, String> messageFactory;

    @SuppressWarnings("unchecked")
    public void init() {
        this.requestHandler = (RequestHandler<String, String>)applicationContext.getBean(REQUEST_HANDLER_BEAN_NAME);
        this.messageFactory = (MessageFactory<HttpServletRequest, String>)applicationContext.getBean(MESSAGE_FACTORY_BEAN_NAME);
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Message<String> requestMessage = messageFactory.createMessage(request);
        Message<String> responseMessage = requestHandler.processRequestMessage(requestMessage);
        try {
            response.getOutputStream().write(responseMessage.getContent().getBytes());
        } catch (IOException e) {
            log.error("Could not write content to response: ", e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
