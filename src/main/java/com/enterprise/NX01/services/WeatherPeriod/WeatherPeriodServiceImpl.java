package com.enterprise.NX01.services.WeatherPeriod;

import com.enterprise.NX01.DTOs.RainPeriodCountDTO;
import com.enterprise.NX01.models.WeatherPeriod;
import com.enterprise.NX01.repositories.WeatherPeriod.WeatherPeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherPeriodServiceImpl implements WeatherPeriodService {

    private WeatherPeriodRepository repository;

    @Autowired
    public WeatherPeriodServiceImpl(WeatherPeriodRepository repository) {
        this.repository = repository;
    }

    @Override
    public WeatherPeriod getByMaxStartDate() {
        return repository.getByMaxStartDate();
    }

    @Override
    public List<WeatherPeriod> save(Iterable<WeatherPeriod> weatherPeriods) {
        return repository.save(weatherPeriods);
    }

    @Override
    public RainPeriodCountDTO getRainPeriods() {
        List<WeatherPeriod> weatherPeriodList = repository.getRainPeriods();

        RainPeriodCountDTO rainPeriodCountDTO = new RainPeriodCountDTO();
        rainPeriodCountDTO.setWeatherPeriodList(weatherPeriodList);
        rainPeriodCountDTO.setRainPeriodCount(weatherPeriodList.size());

        return rainPeriodCountDTO;
    }

    @Override
    public Integer getDroughtCount() {
        return repository.getDroughtCount();
    }

    @Override
    public Integer getOptimalWeatherCount() {
        return repository.getOptimalWeatherCount();
    }
}
