package com.enterprise.NX01.services.SolarSystemSnapshot;

import com.enterprise.NX01.models.SolarSystemSnapshot;
import com.enterprise.NX01.repositories.SolarSystemSnapshot.SolarSystemSnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.enterprise.NX01.helpers.DateHelper.convertToDate;
import static com.enterprise.NX01.helpers.DateHelper.convertToLocalDate;
import static com.enterprise.NX01.helpers.SolarSystemSnapshotWeatherHelper.calculateWeather;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class SolarSystemSnapshotServiceImpl implements SolarSystemSnapshotService {

    private SolarSystemSnapshotRepository repository;

    @Value("${start.date}")
    private String startDateStr;

    @Autowired
    public SolarSystemSnapshotServiceImpl(SolarSystemSnapshotRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long getCount() {
        return repository.getCount();
    }

    @Override
    public Page<SolarSystemSnapshot> getAll(Pageable page) {
        return repository.getAll(page);
    }

    @Override
    public SolarSystemSnapshot getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public SolarSystemSnapshot getByDate(Date date) {
        SolarSystemSnapshot solarSystemSnapshot = repository.getByDate(date);
        if (solarSystemSnapshot != null)
            return solarSystemSnapshot;

        LocalDate startDate = LocalDate.parse(startDateStr);

        LocalDate currentDate = convertToLocalDate(date);

        solarSystemSnapshot = new SolarSystemSnapshot();

        solarSystemSnapshot.setDate(convertToDate(currentDate));
        solarSystemSnapshot.setDay(DAYS.between(startDate, currentDate));
        solarSystemSnapshot.setWeather(calculateWeather(startDate, currentDate, solarSystemSnapshot));

        return solarSystemSnapshot;
    }

    @Override
    public SolarSystemSnapshot getByDay(Long day) {
        return repository.getByDay(day);
    }

    @Override
    public SolarSystemSnapshot getByLastDate() {
        return repository.getByLastDate();
    }

    @Override
    public List<SolarSystemSnapshot> save(Iterable<SolarSystemSnapshot> solarSystemSnapshots) {
        return repository.save(solarSystemSnapshots);
    }
}
