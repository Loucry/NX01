package com.enterprise.NX01.repositories.WeatherPeriod;

import com.enterprise.NX01.models.WeatherPeriod;

import java.util.List;

public interface WeatherPeriodRepository {

    WeatherPeriod getByMaxStartDate();

    List<WeatherPeriod> save(Iterable<WeatherPeriod> weatherPeriods);

    Integer getOptimalWeatherCount();

    Integer getDroughtCount();

    List<WeatherPeriod> getRainPeriods();
}
