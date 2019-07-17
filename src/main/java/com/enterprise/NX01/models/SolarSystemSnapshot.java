package com.enterprise.NX01.models;

import com.enterprise.NX01.enums.WeatherType;
import com.enterprise.NX01.helpers.SolarSystemSnapshotWeatherHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

import static com.enterprise.NX01.helpers.SolarSystemSnapshotWeatherHelper.calculatePerimeter;

@Entity
@Table(name = "solar_system_snapshot")
public class SolarSystemSnapshot {

    private Long id;

    private Long day;

    private WeatherType weather = WeatherType.NORMAL;

    private Date date;

    private Double planetsTrianglePerimeter;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "day")
    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    @Column(name = "weather")
    public WeatherType getWeather() {
        return weather;
    }

    public void setWeather(WeatherType weather) {
        this.weather = weather;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Transient
    @JsonIgnore
    public Double getPlanetsTrianglePerimeter() {
        if (planetsTrianglePerimeter == null)
            planetsTrianglePerimeter = calculatePerimeter(this);
        return planetsTrianglePerimeter;
    }

    public void setPlanetsTrianglePerimeter(Double planetsTrianglePerimeter) {
        this.planetsTrianglePerimeter = planetsTrianglePerimeter;
    }
}
