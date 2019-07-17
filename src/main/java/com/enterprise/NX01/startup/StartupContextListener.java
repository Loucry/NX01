package com.enterprise.NX01.startup;

import com.enterprise.NX01.enums.WeatherType;
import com.enterprise.NX01.helpers.SolarSystemSnapshotWeatherHelper;
import com.enterprise.NX01.models.SolarSystemSnapshot;
import com.enterprise.NX01.models.WeatherPeriod;
import com.enterprise.NX01.services.SolarSystemSnapshot.SolarSystemSnapshotService;
import com.enterprise.NX01.services.WeatherPeriod.WeatherPeriodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static com.enterprise.NX01.helpers.DateHelper.convertToDate;
import static com.enterprise.NX01.helpers.DateHelper.convertToLocalDate;
import static com.enterprise.NX01.helpers.SolarSystemSnapshotWeatherHelper.calculateWeather;
import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class StartupContextListener {

    private SolarSystemSnapshotService solarSystemSnapshotService;

    private WeatherPeriodService weatherPeriodService;

    private final static Logger logger = LoggerFactory.getLogger(StartupContextListener.class);

    @Value("${start.date}")
    private String startDateStr;

    @Autowired
    public StartupContextListener(SolarSystemSnapshotService solarSystemSnapshotService, WeatherPeriodService weatherPeriodService) {
        this.solarSystemSnapshotService = solarSystemSnapshotService;
        this.weatherPeriodService = weatherPeriodService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {

        LocalDate startDate = LocalDate.parse(startDateStr);

        SolarSystemSnapshotWeatherHelper.setStartDate(startDate);

        LocalDate dateToCalculate;

        SolarSystemSnapshot lastSnapshot = solarSystemSnapshotService.getByLastDate();
        if (lastSnapshot != null) {
            dateToCalculate = convertToLocalDate(lastSnapshot.getDate()).plusDays(1);
        } else {
            dateToCalculate = startDate;
        }

        Long remainingDays = DAYS.between(dateToCalculate, startDate.plusYears(10));

        LocalDate currentDate = dateToCalculate;
        List<SolarSystemSnapshot> snapshots = new LinkedList<>();

        Stack<WeatherPeriod> weatherPeriodStack = new Stack<>();
        WeatherPeriod wp = weatherPeriodService.getByMaxStartDate();
        if (wp != null) {
            weatherPeriodStack.push(wp);
        }

        for (int i = 0; i < remainingDays; i++) {
            SolarSystemSnapshot solarSystemSnapshot = new SolarSystemSnapshot();

            solarSystemSnapshot.setDate(convertToDate(currentDate));
            solarSystemSnapshot.setDay(DAYS.between(startDate, currentDate));
            solarSystemSnapshot.setWeather(calculateWeather(startDate, currentDate, solarSystemSnapshot));

            snapshots.add(solarSystemSnapshot);

            if (weatherPeriodStack.isEmpty()) {
                initWeatherPeriodStack(weatherPeriodStack, solarSystemSnapshot);
            } else {
                WeatherPeriod lastWeatherPeriod = weatherPeriodStack.peek();
                if (!lastWeatherPeriod.getWeatherType().equals(solarSystemSnapshot.getWeather())) {
                    weatherPeriodStack.push(initNewWeatherPeriod(solarSystemSnapshot));
                } else {
                    lastWeatherPeriod.setEndSnapshot(solarSystemSnapshot);

                    if (lastWeatherPeriod.getWeatherType().equals(WeatherType.RAIN)) {
                        if (lastWeatherPeriod.getPeakRainSnapshot().getPlanetsTrianglePerimeter() <= solarSystemSnapshot.getPlanetsTrianglePerimeter()) {
                            lastWeatherPeriod.setPeakRainSnapshot(solarSystemSnapshot);
                        }
                    }
                }
            }

            currentDate = currentDate.plusDays(1);
        }

        solarSystemSnapshotService.save(snapshots);
        weatherPeriodService.save(weatherPeriodStack);
    }

    private void initWeatherPeriodStack(Stack<WeatherPeriod> stack, SolarSystemSnapshot snapshot) {
        stack.push(initNewWeatherPeriod(snapshot));
    }

    private WeatherPeriod initNewWeatherPeriod(SolarSystemSnapshot snapshot) {
        WeatherPeriod wp = new WeatherPeriod();
        wp.setStartSnapshot(snapshot);
        wp.setEndSnapshot(snapshot);
        wp.setWeatherType(snapshot.getWeather());

        if (wp.getWeatherType().equals(WeatherType.RAIN))
            wp.setPeakRainSnapshot(snapshot);

        return wp;
    }
}
