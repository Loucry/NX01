package com.enterprise.NX01.repositories.WeatherPeriod;

import com.enterprise.NX01.enums.WeatherType;
import com.enterprise.NX01.models.WeatherPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherPeriodRepositoryImpl implements WeatherPeriodRepository {

    private WeatherPeriodJpaRepository jpaRepository;

    @Autowired
    public WeatherPeriodRepositoryImpl(WeatherPeriodJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public WeatherPeriod getByMaxStartDate() {
        return jpaRepository.getFirstByStartDate();
    }

    @Override
    public List<WeatherPeriod> save(Iterable<WeatherPeriod> weatherPeriods) {
        return jpaRepository.save(weatherPeriods);
    }

    @Override
    public Integer getOptimalWeatherCount() {
        return jpaRepository.countByWeatherType(WeatherType.OPTIMAL);
    }

    @Override
    public Integer getDroughtCount() {
        return jpaRepository.countByWeatherType(WeatherType.DROUGHT);
    }

    @Override
    public List<WeatherPeriod> getRainPeriods() {
        return jpaRepository.getAllByWeatherType(WeatherType.RAIN);
    }
}
