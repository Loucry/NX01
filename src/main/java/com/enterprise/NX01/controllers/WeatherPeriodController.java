package com.enterprise.NX01.controllers;

import com.enterprise.NX01.DTOs.RainPeriodCountDTO;
import com.enterprise.NX01.services.WeatherPeriod.WeatherPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/WeatherPeriod")
public class WeatherPeriodController {

    private WeatherPeriodService service;

    @Autowired
    public WeatherPeriodController(WeatherPeriodService service) {
        this.service = service;
    }

    @RequestMapping(path = "/drought/count", method = RequestMethod.GET)
    public @ResponseBody Integer getDroughtCount() {
        return service.getDroughtCount();
    }

    @RequestMapping(path = "/rain/count", method = RequestMethod.GET)
    public @ResponseBody RainPeriodCountDTO getRainCount() {
        return service.getRainPeriods();
    }

    @RequestMapping(path = "/optimal/count", method = RequestMethod.GET)
    public @ResponseBody Integer getOptimalWeatherCount() {
        return service.getOptimalWeatherCount();
    }
}
