package de.jeha.demo.springboot.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class DemoEventsResponse {

    @JsonProperty
    private List<DemoEvent> demoEvents;

    public DemoEventsResponse(List<DemoEvent> demoEvents) {
        this.demoEvents = demoEvents;
    }

}
