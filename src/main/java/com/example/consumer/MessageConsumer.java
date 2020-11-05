package com.example.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.Session;

@Component
public class MessageConsumer {

    @JmsListener(destination = "${jms.test-queue}")
    public void processToDo(Message<String> message, Session session) {
        System.out.println(message.getPayload());
    }

}
