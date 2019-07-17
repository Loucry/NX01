package com.enterprise.NX01.services.WeatherPeriod;

import com.enterprise.NX01.DTOs.RainPeriodCountDTO;
import com.enterprise.NX01.models.WeatherPeriod;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WeatherPeriodService {

    @Transactional(readOnly = true)
    WeatherPeriod getByMaxStartDate();

    @Transactional(rollbackFor = Exception.class)
    List<WeatherPeriod> save(Iterable<WeatherPeriod> weatherPeriods);

    @Transactional(readOnly = true)
    RainPeriodCountDTO getRainPeriods();

    @Transactional(readOnly = true)
    Integer getDroughtCount();

    @Transactional(readOnly = true)
    Integer getOptimalWeatherCount();
}
