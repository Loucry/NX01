package com.enterprise.NX01.helpers;

import com.enterprise.NX01.enums.WeatherType;
import com.enterprise.NX01.models.SolarSystemSnapshot;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class SolarSystemSnapshotWeatherHelper {

    private final static Planet FERENGI = new Planet(500, -1);

    private final static Planet VULCANO = new Planet(1000, 5);

    private final static Planet BETASOIDE = new Planet(2000, -3);

    private final static Planet SUN = new Planet(0, 0);

    private final static Double errorMargin = 0.0001d;

    private static LocalDate startDate = null;

    public static void setStartDate(LocalDate _startDate) {
        if (startDate == null)
            startDate = _startDate;
    }

    public static WeatherType calculateWeather(LocalDate startDate, LocalDate currentDate, SolarSystemSnapshot snapshot) {

        Planet ferengi = Planet.initCopy(FERENGI);
        Planet vulcano = Planet.initCopy(VULCANO);
        Planet betasoide = Planet.initCopy(BETASOIDE);
        Planet sun = Planet.initCopy(SUN);

        Long numberOfDays = DAYS.between(startDate, currentDate);

        transformPosition(ferengi, numberOfDays);
        transformPosition(vulcano, numberOfDays);
        transformPosition(betasoide, numberOfDays);

        if (checkColinearity(ferengi, vulcano, betasoide)) {
            if (checkColinearity(ferengi, vulcano, sun)) {
                return WeatherType.DROUGHT;
            } else {
                return WeatherType.OPTIMAL;
            }
        } else {
            if (sunInsideTriangle(sun, ferengi, vulcano, betasoide)) {
                snapshot.setPlanetsTrianglePerimeter(calculatePerimeter(ferengi, vulcano, betasoide));
                return WeatherType.RAIN;
            } else {
                return WeatherType.NORMAL;
            }
        }
    }

    public static Double calculatePerimeter(SolarSystemSnapshot snapshot) {

        Planet ferengi = Planet.initCopy(FERENGI);
        Planet vulcano = Planet.initCopy(VULCANO);
        Planet betasoide = Planet.initCopy(BETASOIDE);

        Long numberOfDays = DAYS.between(startDate, DateHelper.convertToLocalDate(snapshot.getDate()));

        transformPosition(ferengi, numberOfDays);
        transformPosition(vulcano, numberOfDays);
        transformPosition(betasoide, numberOfDays);

        return calculatePerimeter(ferengi, vulcano, betasoide);
    }

    private static Double calculatePerimeter(Planet ferengi, Planet vulcano, Planet betasoide) {
        Double dfv = calculateDistanceBetweenPlanets(ferengi, vulcano);
        Double dvb = calculateDistanceBetweenPlanets(vulcano, betasoide);
        Double dfb = calculateDistanceBetweenPlanets(ferengi, betasoide);
        return dfv + dvb + dfb;
    }

    private static Double calculateDistanceBetweenPlanets(Planet a, Planet b) {
        return Math.sqrt(Math.pow(b.x - a.x , 2) + Math.pow(b.y - a.y ,2));
    }

    private static Boolean sunInsideTriangle(Planet p, Planet p0, Planet p1, Planet p2) {
        Double w1 = ( p0.x * (p2.y - p0.y) + (p.y - p0.y)*(p2.x - p0.x) - p.x*(p2.y-p0.y) ) / ( (p1.y - p0.y)*(p2.x-p0.x) - (p1.x-p0.x)*(p2.y-p0.y) );

        if (w1 < 0)
            return false;

        Double w2 = ( p.y - p0.y - w1*(p1.y - p0.y ) ) / (p2.y - p0.y);

        return w2 >= 0 && (w1+w2) <= 1;
    }

    private static void transformPosition(Planet planet, Long numberOfDays) {
        planet.setY(planet.getRadius() * Math.sin(Math.toRadians(90+(numberOfDays*planet.rotationDegreesPerDay))));
        planet.setX(planet.getRadius() * Math.cos(Math.toRadians(90+(numberOfDays*planet.rotationDegreesPerDay))));
    }

    private static Boolean checkColinearity(Planet a, Planet b, Planet c) {
        Double result = a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y);
        return isLessThanErrorMargin(result);
    }

    private static boolean isLessThanErrorMargin(Double a) {

        return Math.abs(a) < errorMargin;
    }

    private static class Planet {

        public Double x;

        public Double y;

        private Integer radius;

        private Integer rotationDegreesPerDay;

        public Planet(Integer radius, Integer rotationDegreesPerDay) {
            this.radius = radius;
            this.rotationDegreesPerDay = rotationDegreesPerDay;
        }

        public Double getX() {
            return x;
        }

        public void setX(Double x) {
            x = BigDecimal.valueOf(x).setScale(3, RoundingMode.HALF_UP).doubleValue();
            if (isLessThanErrorMargin(x))
                this.x = 0d;
            else
                this.x = x;
        }

        public Double getY() {
            return y;
        }

        public void setY(Double y) {
            y = BigDecimal.valueOf(y).setScale(3, RoundingMode.HALF_UP).doubleValue();
            if (isLessThanErrorMargin(y))
                this.y = 0d;
            else
                this.y = y;
        }

        public Integer getRadius() {
            return radius;
        }

        public Integer getRotationDegreesPerDay() {
            return rotationDegreesPerDay;
        }

        public static Planet initCopy(Planet planet) {
            Planet retPlanet = new Planet(planet.getRadius(), planet.getRotationDegreesPerDay());

            retPlanet.setX(0d);
            retPlanet.setY(Double.valueOf(retPlanet.getRadius()));

            return retPlanet;
        }
    }
}
