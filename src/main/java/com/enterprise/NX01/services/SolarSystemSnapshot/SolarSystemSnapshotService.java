package com.enterprise.NX01.services.SolarSystemSnapshot;

import com.enterprise.NX01.models.SolarSystemSnapshot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface SolarSystemSnapshotService {

    @Transactional(readOnly = true)
    Long getCount();

    @Transactional(readOnly = true)
    Page<SolarSystemSnapshot> getAll(Pageable page);

    @Transactional(readOnly = true)
    SolarSystemSnapshot getById(Long id);

    @Transactional(readOnly = true)
    SolarSystemSnapshot getByDate(Date date);

    @Transactional(readOnly = true)
    SolarSystemSnapshot getByDay(Long day);

    @Transactional(readOnly = true)
    SolarSystemSnapshot getByLastDate();

    @Transactional(rollbackFor = Exception.class)
    List<SolarSystemSnapshot> save(Iterable<SolarSystemSnapshot> solarSystemSnapshots);
}
