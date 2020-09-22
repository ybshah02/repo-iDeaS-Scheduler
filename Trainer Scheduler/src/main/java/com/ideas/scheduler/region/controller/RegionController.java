package com.ideas.scheduler.region.controller;

import com.ideas.scheduler.client.exception.NoDataFoundException;
import com.ideas.scheduler.region.exception.RegionNotFoundException;
import com.ideas.scheduler.region.repository.RegionRepository;
import com.ideas.scheduler.region.service.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegionController {

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/regions")
    public List<Region> getRegions() {
        List<Region> regions = regionRepository.findAll();
        if (regions.isEmpty()) {
            throw new NoDataFoundException();
        } else {
            return regions;
        }
    }

    @GetMapping("/regions/{id}")
    public Region getRegionById(@PathVariable("id") int id) {
        Region region = regionRepository.findById(id);
        if (region != null) {
            return region;
        } else {
            throw new RegionNotFoundException(id);
        }
    }

    @GetMapping("/regions/{id}/active")
    public boolean getRegionActive(@PathVariable("id") int id) {
        Region region = regionRepository.findById(id);
        if (region != null) {
            return region.isActive();
        } else {
            throw new RegionNotFoundException(id);
        }
    }

    @PostMapping("/regions")
    public void addRegion(@Validated @RequestBody Region region) {
        regionRepository.save(region);
    }

    @DeleteMapping("/regions/{id}")
    public void hardDeleteRegion(@PathVariable("id") int id) {

        if (regionRepository.findById(id) != null) {
            regionRepository.deleteById(id);
        } else {
            throw new RegionNotFoundException(id);
        }
    }

    @PutMapping("/regions/{id}/active")
    public void softDeleteRegion(@PathVariable("id") int id) {
        Region region = regionRepository.findById(id);
        if (region != null) {
            region.setInactive();
            regionRepository.save(region);
        } else {
            throw new RegionNotFoundException(id);
        }
    }

    @PutMapping("/regions/{id}")
    public void modifyRegion(@PathVariable("id") int id, @RequestBody Region region) {
        Region regionToModify = regionRepository.findById(id);

        if (regionToModify == null) {
            throw new RegionNotFoundException(id);
        } else {
            region.setID(regionToModify.getID());

            if (!StringUtils.isEmpty(region.getRegionName())) {
                regionToModify.setRegionName(region.getRegionName());
            }

            if (!StringUtils.isEmpty(region.getTimezone())) {
                regionToModify.setRegionName(region.getTimezone());
            }
            regionRepository.save(regionToModify);
        }
    }

}
