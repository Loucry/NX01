package com.enterprise.NX01.repositories.SolarSystemSnapshot;

import com.enterprise.NX01.models.SolarSystemSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface SolarSystemSnapshotJpaRepository extends JpaRepository<SolarSystemSnapshot, Long> {

    SolarSystemSnapshot getByDate(Date date);

    SolarSystemSnapshot getByDay(Long day);

    @Query(value = "select sss from SolarSystemSnapshot sss where sss.date = (select max(ssss.date) from SolarSystemSnapshot ssss) ")
    SolarSystemSnapshot getFirstByDateOrderByDateDesc();
}
