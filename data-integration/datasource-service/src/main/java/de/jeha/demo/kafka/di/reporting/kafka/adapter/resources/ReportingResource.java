package de.jeha.demo.kafka.di.reporting.kafka.adapter.resources;

import de.jeha.demo.kafka.di.datasource.service.api.DemoEventsResponse;
import de.jeha.demo.kafka.di.datasource.service.api.DemoEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author jenshadlich@googlemail.com
 */
@RestController
public class ReportingResource {

    @RequestMapping(value = "/reporting/v1/demoEvents", produces = "application/json")
    public DemoEventsResponse demoEvents() {
        return new DemoEventsResponse(
                Arrays.asList(
                        new DemoEvent("1"),
                        new DemoEvent("2")
                ));
    }

}
