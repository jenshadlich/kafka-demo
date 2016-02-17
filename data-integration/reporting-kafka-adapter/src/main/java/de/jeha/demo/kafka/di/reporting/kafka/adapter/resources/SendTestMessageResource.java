package de.jeha.demo.kafka.di.reporting.kafka.adapter.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
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
        final Message message =
                MessageBuilder.withPayload("This is a test").setHeader("topic", "test").build();

        channel.send(message);

        return "{ status: \"success\" }";
    }

}
