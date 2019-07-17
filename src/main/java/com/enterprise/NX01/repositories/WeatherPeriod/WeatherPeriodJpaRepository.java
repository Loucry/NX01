package com.enterprise.NX01.repositories.WeatherPeriod;

import com.enterprise.NX01.enums.WeatherType;
import com.enterprise.NX01.models.WeatherPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeatherPeriodJpaRepository extends JpaRepository<WeatherPeriod, Long> {

    @Query(value = "select wp from WeatherPeriod wp where wp.startSnapshot.date = (select max(wps.startSnapshot.date) from WeatherPeriod wps) ")
    WeatherPeriod getFirstByStartDate();

    Integer countByWeatherType(WeatherType weatherType);

    @Query(value = "select wp from WeatherPeriod wp " +
            "left join fetch wp.startSnapshot " +
            "left join fetch wp.endSnapshot " +
            "left join fetch wp.peakRainSnapshot " +
            "where wp.weatherType = ?1")
    List<WeatherPeriod> getAllByWeatherType(WeatherType weatherType);
}
