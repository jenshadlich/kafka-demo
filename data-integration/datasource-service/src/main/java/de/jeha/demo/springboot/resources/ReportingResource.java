package de.jeha.demo.springboot.resources;

import de.jeha.demo.springboot.api.DemoEvent;
import de.jeha.demo.springboot.api.DemoEventsResponse;
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
