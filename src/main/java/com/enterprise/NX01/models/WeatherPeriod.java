package com.enterprise.NX01.models;

import com.enterprise.NX01.enums.WeatherType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "weather_period")
public class WeatherPeriod {

    private Long id;

    private SolarSystemSnapshot startSnapshot;

    private SolarSystemSnapshot endSnapshot;

    private WeatherType weatherType;

    private SolarSystemSnapshot peakRainSnapshot;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JoinColumn(name = "startSnapshotId")
    @OneToOne(optional = false, fetch = LAZY)
    public SolarSystemSnapshot getStartSnapshot() {
        return startSnapshot;
    }

    public void setStartSnapshot(SolarSystemSnapshot startSnapshot) {
        this.startSnapshot = startSnapshot;
    }

    @JoinColumn(name = "endSnapshotId")
    @OneToOne(optional = false, fetch = LAZY)
    public SolarSystemSnapshot getEndSnapshot() {
        return endSnapshot;
    }

    public void setEndSnapshot(SolarSystemSnapshot endSnapshot) {
        this.endSnapshot = endSnapshot;
    }

    @Column(name = "weatherType")
    public WeatherType getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    @JoinColumn(name = "peakRainSnapshotId")
    @OneToOne(fetch = LAZY)
    public SolarSystemSnapshot getPeakRainSnapshot() {
        return peakRainSnapshot;
    }

    public void setPeakRainSnapshot(SolarSystemSnapshot peakRainSnapshot) {
        this.peakRainSnapshot = peakRainSnapshot;
    }
}
