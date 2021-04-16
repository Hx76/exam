package com.onlineexam.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
@EnableJms
public class QueueProduce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000)
    public void produceMessageScheduled(){
        jmsMessagingTemplate.convertAndSend(queue,"update");
        System.out.println("发了");
    }
}
