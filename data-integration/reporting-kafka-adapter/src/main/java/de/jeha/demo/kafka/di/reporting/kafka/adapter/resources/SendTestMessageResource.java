package de.jeha.demo.kafka.di.reporting.kafka.adapter.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
@RestController
public class SendTestMessageResource {

    @Autowired
    @Qualifier("inputToKafka")
    private MessageChannel channel;

    @RequestMapping(value = "/sendTestMessage", produces = "application/json")
    public String sendTestMessage() throws IOException {

        channel.send(MessageBuilder.withPayload("This is a test")
                .setHeader(KafkaHeaders.TOPIC, "test")
                .build());

        return "{ status: \"success\" }";
    }

}
