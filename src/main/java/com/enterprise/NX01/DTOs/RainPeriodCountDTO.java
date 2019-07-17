package com.enterprise.NX01.DTOs;

import com.enterprise.NX01.models.WeatherPeriod;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class RainPeriodCountDTO implements Serializable {

    private Integer rainPeriodCount;

    private List<WeatherPeriod> weatherPeriodList = new LinkedList<>();

    public Integer getRainPeriodCount() {
        return rainPeriodCount;
    }

    public void setRainPeriodCount(Integer rainPeriodCount) {
        this.rainPeriodCount = rainPeriodCount;
    }

    public List<WeatherPeriod> getWeatherPeriodList() {
        return weatherPeriodList;
    }

    public void setWeatherPeriodList(List<WeatherPeriod> weatherPeriodList) {
        this.weatherPeriodList = weatherPeriodList;
    }
}
