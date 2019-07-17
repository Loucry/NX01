package com.enterprise.NX01.repositories.SolarSystemSnapshot;

import com.enterprise.NX01.models.SolarSystemSnapshot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface SolarSystemSnapshotRepository {

    Long getCount();

    Page<SolarSystemSnapshot> getAll(Pageable page);

    SolarSystemSnapshot getById(Long id);

    SolarSystemSnapshot getByDate(Date date);

    SolarSystemSnapshot getByDay(Long day);

    SolarSystemSnapshot getByLastDate();

    List<SolarSystemSnapshot> save(Iterable<SolarSystemSnapshot> solarSystemSnapshots);
}
