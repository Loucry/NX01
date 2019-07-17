package com.enterprise.NX01.controllers;

import com.enterprise.NX01.models.SolarSystemSnapshot;
import com.enterprise.NX01.services.SolarSystemSnapshot.SolarSystemSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/SolarSystemSnapshot")
public class SolarSystemSnapshotController {

    private SolarSystemSnapshotService service;

    @Autowired
    public SolarSystemSnapshotController(SolarSystemSnapshotService service) {
        this.service = service;
    }

    @RequestMapping(path = "/date/{date}", method = RequestMethod.GET)
    public @ResponseBody SolarSystemSnapshot getByDate(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        return service.getByDate(date);
    }

    @RequestMapping(path = "/day/{day}", method = RequestMethod.GET)
    public @ResponseBody SolarSystemSnapshot getByDay(@PathVariable Long day) {
        return service.getByDay(day);
    }
}
