package com.enterprise.NX01.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum WeatherType {

    DROUGHT("Drought"), OPTIMAL("Optimal"), RAIN("Rain"), NORMAL("Normal");

    private String name;

    @JsonValue
    public String getName() {
        return name;
    }

    WeatherType(String name) { this.name = name; }
}
