package com.enterprise.NX01.repositories.SolarSystemSnapshot;

import com.enterprise.NX01.models.SolarSystemSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SolarSystemSnapshotRepositoryImpl implements SolarSystemSnapshotRepository {

    private SolarSystemSnapshotJpaRepository jpaRepository;

    @Autowired
    public SolarSystemSnapshotRepositoryImpl(SolarSystemSnapshotJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Long getCount() {
        return jpaRepository.count();
    }

    @Override
    public Page<SolarSystemSnapshot> getAll(Pageable page) {
        return jpaRepository.findAll(page);
    }

    @Override
    public SolarSystemSnapshot getById(Long id) {
        return jpaRepository.findOne(id);
    }

    @Override
    public SolarSystemSnapshot getByDate(Date date) {
        return jpaRepository.getByDate(date);
    }

    @Override
    public SolarSystemSnapshot getByDay(Long day) {
        return jpaRepository.getByDay(day);
    }

    @Override
    public SolarSystemSnapshot getByLastDate() {
        return jpaRepository.getFirstByDateOrderByDateDesc();
    }

    @Override
    public List<SolarSystemSnapshot> save(Iterable<SolarSystemSnapshot> solarSystemSnapshots) {
        return jpaRepository.save(solarSystemSnapshots);
    }
}
