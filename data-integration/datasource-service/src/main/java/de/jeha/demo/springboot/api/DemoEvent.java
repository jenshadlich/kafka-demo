package de.jeha.demo.springboot.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jenshadlich@googlemail.com
 */
public class DemoEvent {

    @JsonProperty
    private final String id;

    @JsonCreator
    public DemoEvent(@JsonProperty("id") String id) {
        this.id = id;
    }

}
