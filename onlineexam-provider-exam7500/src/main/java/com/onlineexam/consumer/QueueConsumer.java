package com.onlineexam.consumer;

import com.onlineexam.service.ExamService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.TextMessage;
@Component
public class QueueConsumer {
    @Resource
    ExamService service;
    @JmsListener(destination = "${myqueue}")
    public void receive(TextMessage textMessage) throws JMSException {
        if (textMessage.getText()!=null){
            service.updateState();
        }
    }
}
