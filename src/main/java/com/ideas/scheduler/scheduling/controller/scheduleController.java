package com.ideas.scheduler.scheduling.controller;

import com.ideas.scheduler.client.exception.NoDataFoundException;
import com.ideas.scheduler.client.repository.ClientLGProgressionRepository;
import com.ideas.scheduler.client.service.ClientLGProgression;
import com.ideas.scheduler.region.exception.RegionNotFoundException;
import com.ideas.scheduler.region.repository.RegionRepository;
import com.ideas.scheduler.region.service.Region;
import com.ideas.scheduler.resource.exception.ResourceNotFoundException;
import com.ideas.scheduler.resource.repository.CapabilitiesRepository;
import com.ideas.scheduler.resource.repository.ResourceLanguageRepository;
import com.ideas.scheduler.resource.repository.ResourceRepository;
import com.ideas.scheduler.resource.service.Resource;
import com.ideas.scheduler.scheduling.exception.ActivatedResourceNotFoundException;
import com.ideas.scheduler.scheduling.exception.ResourceAvailabilityNotFoundException;
import com.ideas.scheduler.scheduling.repository.ActivatedResourceRepository;
import com.ideas.scheduler.scheduling.repository.BookingRepository;
import com.ideas.scheduler.scheduling.repository.ResourceAvailabilityRepository;
import com.ideas.scheduler.scheduling.repository.WorkLeaveRepository;
import com.ideas.scheduler.scheduling.service.ActivatedResource;
import com.ideas.scheduler.scheduling.service.Booking;
import com.ideas.scheduler.scheduling.service.ResourceAvailability;
import com.ideas.scheduler.scheduling.service.WorkLeave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class scheduleController {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ResourceLanguageRepository resourceLanguageRepository;

    @Autowired
    private CapabilitiesRepository capabilitiesRepository;

    @Autowired
    private ActivatedResourceRepository activatedResourceRepository;

    @Autowired
    private ResourceAvailabilityRepository resourceAvailabilityRepository;

    @Autowired
    private WorkLeaveRepository workLeaveRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ClientLGProgressionRepository clientLGProgressionRepository;

    @GetMapping("/activated-resources")
    List<ActivatedResource> getActivatedResources(){
        return (List<ActivatedResource>) activatedResourceRepository.findAll();
    }

    @GetMapping("/activated-resources/{regionId}")
    List<ActivatedResource> getActivatedResourcesByRegion(@PathVariable("regionId") int regionId){
        return activatedResourceRepository.findAllByRegionId(regionId);
    }

    @GetMapping("/activated-resources/{regionId}/{resourceId}")
    ActivatedResource getActivatedResourceByResourceId(@PathVariable("regionId") int regionId, @PathVariable("resourceId") int resourceId){
        return activatedResourceRepository.findByRegionIdAndResourceId(regionId, resourceId);
    }

    /*
    A resource isn't activated for scheduling until some button/entry in the front end calls this function to set the resource's activated for scheduling
    variable to true and create a new entry in the activated resource db --- stores resourceId that is a foreign key to resources db, the regionId, and the
    start date and end date of the resource's shift.
    */

    @PostMapping("/activated-resources")
    public void activateResource(@Validated @RequestBody ActivatedResource activatedResource){
        Resource resource = resourceRepository.findById(activatedResource.getResourceId());

        if (resource != null){
            resource.setActivatedForSchedulingTrue();
            resourceRepository.save(resource);
        }else{
            throw new ResourceNotFoundException(activatedResource.getResourceId());
        }

        activatedResourceRepository.save(activatedResource);
    }

    @PutMapping("/activated-resources/{resourceId}")
    public void disableResource(@PathVariable("resourceId") int resourceId){

        if (activatedResourceRepository.findByResourceId(resourceId) != null){
            activatedResourceRepository.deleteByResourceId(resourceId);
        }else{
            throw new ActivatedResourceNotFoundException(resourceId);
        }

        Resource resource = resourceRepository.findById(resourceId);
        if (resource != null) {
            resource.setActivatedForSchedulingFalse();
        }else {
            throw new ResourceNotFoundException(resourceId);
        }
    }

    /*
    Once a resource is activated, they must declare their availability through the calendar -- front end passes a list of dates (could be any combination of days,
    or simply every day between the start and end date) which is parsed through and stored in resource_availability db with the start and end time given.
     */

    @PostMapping("/activated-resources/availability/{regionId}/{resourceId}")
    void assignAvailability(@PathVariable("regionId") int regionId, @PathVariable("resourceId") int resourceId, @RequestParam List<LocalDateTime> daysToAssign,
                            @RequestParam LocalTime startTime, @RequestParam LocalTime endTime){

        Region region = regionRepository.findById(regionId);
        if (region != null){
            throw new RegionNotFoundException(regionId);
        }else {

            String tzPrefix = region.getTimezone().substring(0, 2);
            String tzOffset = region.getTimezone().substring(3);

            if (daysToAssign.isEmpty()){
                throw new NoDataFoundException();
            }else {
                for (LocalDateTime day : daysToAssign) {
                    if (day != null) {
                        ZoneId fromTimeZone = ZoneId.ofOffset(tzPrefix, ZoneOffset.ofHours(Integer.parseInt(tzOffset)));
                        ZoneId toTimeZone = ZoneId.ofOffset("UTC", ZoneOffset.UTC);
                        ZonedDateTime inputZone = day.atZone(fromTimeZone);
                        ZonedDateTime utcZoned = inputZone.withZoneSameInstant(toTimeZone);

                        LocalDate date = utcZoned.toLocalDate();

                        startTime = startTime.atOffset(ZoneOffset.UTC).toLocalTime();
                        endTime = endTime.atOffset(ZoneOffset.UTC).toLocalTime();

                        ResourceAvailability resourcesAvailability = new ResourceAvailability(resourceId, date, startTime, endTime, false);
                        resourceAvailabilityRepository.save(resourcesAvailability);
                    }
                }
            }
        }
    }

    @GetMapping("/activated-resources/availability/{regionId}/{resourceId}")
    List<ResourceAvailability> getAvailabilityOfResource(@PathVariable int regionId, @PathVariable int resourceId){
        List<ResourceAvailability> resourceAvailabilities = resourceAvailabilityRepository.findAllByResourceId(resourceId);
        if (resourceAvailabilities.isEmpty()){
            throw new NoDataFoundException();
        }else {
            List<ResourceAvailability> resourceAvailabilitiesTZ = new ArrayList<>();

            Region region = regionRepository.findById(regionId);
            if (region != null){
                throw new RegionNotFoundException(regionId);
            }else {
                String tzPrefix = region.getTimezone().substring(0, 3);
                int indexOfColon = region.getTimezone().indexOf(':');
                int hoursToAdd = Integer.parseInt(region.getTimezone().substring(3, indexOfColon));
                int minutesToAdd = Integer.parseInt(region.getTimezone().substring(indexOfColon + 1));

                for (ResourceAvailability resourceAvailability : resourceAvailabilities) {
                    if (resourceAvailability != null) {
                        ResourceAvailability tempResource = resourceAvailability;

                        ZoneId fromTimeZone = ZoneId.ofOffset("UTC", ZoneOffset.UTC);
                        ZoneId toTimeZone = ZoneId.ofOffset(tzPrefix, ZoneOffset.ofHoursMinutes(hoursToAdd, minutesToAdd));
                        LocalDateTime date = tempResource.getWorkingDay().atTime(tempResource.getStartTime());
                        ZonedDateTime inputZone = date.atZone(fromTimeZone);
                        ZonedDateTime utcZoned = inputZone.withZoneSameInstant(toTimeZone);

                        LocalTime startTime = tempResource.getStartTime();
                        LocalTime endTime = tempResource.getEndTime();
                        startTime = startTime.plusHours(hoursToAdd);
                        endTime = endTime.plusHours(hoursToAdd);
                        startTime = startTime.plusMinutes(minutesToAdd);
                        endTime = endTime.plusMinutes(minutesToAdd);

                        tempResource.setWorkingDay(utcZoned.toLocalDate());
                        tempResource.setStartTime(startTime);
                        tempResource.setEndTime(endTime);
                        resourceAvailabilitiesTZ.add(tempResource);
                    }
                }
                return resourceAvailabilitiesTZ;
            }
        }
    }

    @GetMapping("/activated-resources/searchByStartAndEndDate/{regionId}/{startDateString}/{endDateString}")
    List<ResourceAvailability> findResourceByStartAndEndDate(@PathVariable("regionId") int regionId,
                                                          @PathVariable String startDateString,
                                                          @PathVariable String endDateString){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(startDateString + " 06:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse(endDateString + " 18:00", formatter);

        List<LocalDate> dates = new ArrayList<>();
        List<ResourceAvailability> availableResources = new ArrayList<>();
        LocalDateTime tempStartDate = startDate;

        while (tempStartDate.isBefore(endDate)){
            dates.add(tempStartDate.toLocalDate());
            tempStartDate = tempStartDate.plusDays(1);
        }

        for (LocalDate day: dates){
            List<ResourceAvailability> resourcesAvailable = resourceAvailabilityRepository.findAllByWorkingDay(day);
            if(resourcesAvailable.isEmpty()){
                throw new NoDataFoundException();
            }else{
                for (ResourceAvailability resourceAvailable : resourcesAvailable) {
                    availableResources.add(resourceAvailable);
                }
            }
        }

        return availableResources;
    }

    @GetMapping("/activated-resources/searchByName/{regionId}/{firstName}/{lastName}")
    ActivatedResource findResourceByName(@PathVariable("regionId") int regionId, @PathVariable("firstName") String firstName,
                                @PathVariable("lastName") String lastName){
        Resource resource = resourceRepository.findByRegionIdAndFirstNameAndLastNameAndActivatedForScheduling(regionId, firstName, lastName, true);
        if (resource != null){
            return activatedResourceRepository.findByResourceId(resource.getID());
        }else{
            throw new ResourceNotFoundException(0);
        }
    }

    @GetMapping("/activated-resources/searchByLanguageAndLS/{regionId}/{languageId}/{learningSessionId}")
    List<Resource> findResourceByLanguageAndLS(@PathVariable("regionId") int regionId, @PathVariable("languageId") int languageId,
                                               @PathVariable("learningSessionId") int learningSessionId){
        List<Resource> resources = resourceRepository.findResourceByLanguageIdAndLearningSessionIdAndRegionId(languageId, learningSessionId, regionId);

        if (resources.isEmpty()){
            throw new NoDataFoundException();
        }else{
            return resources;
        }
    }

    @GetMapping("/activated-resources/{regionId}/work-leaves")
    List<WorkLeave> getWorkLeaves(@PathVariable("regionId") int regionId){
        List<WorkLeave> workLeaves = workLeaveRepository.findAllByRegionId(regionId);
        if(workLeaves.isEmpty()){
            throw new NoDataFoundException();
        }else {
            Region region = regionRepository.findById(regionId);
            if (region != null) {
                List<WorkLeave> workLeavesTZ = new ArrayList<>();
                String tzPrefix = region.getTimezone().substring(0, 3);
                int indexOfColon = region.getTimezone().indexOf(':');
                int hoursToAdd = Integer.parseInt(region.getTimezone().substring(3, indexOfColon));
                int minutesToAdd = Integer.parseInt(region.getTimezone().substring(indexOfColon + 1));

                for (WorkLeave workLeave : workLeaves) {
                    WorkLeave tempWorkLeave = workLeave;
                    ZoneId fromTimeZone = ZoneId.ofOffset("UTC", ZoneOffset.UTC);
                    ZoneId toTimeZone = ZoneId.ofOffset(tzPrefix, ZoneOffset.ofHoursMinutes(hoursToAdd, minutesToAdd));

                    LocalDateTime startDateTimedate = workLeave.getStartDateTime();
                    LocalDateTime endDateTimedate = workLeave.getEndDateTime();

                    ZonedDateTime inputZoneStart = startDateTimedate.atZone(fromTimeZone);
                    ZonedDateTime utcZonedStart = inputZoneStart.withZoneSameInstant(toTimeZone);
                    ZonedDateTime inputZoneEnd = endDateTimedate.atZone(fromTimeZone);
                    ZonedDateTime utcZonedEnd = inputZoneEnd.withZoneSameInstant(toTimeZone);
                    tempWorkLeave.setStartDateTime(utcZonedStart.toLocalDateTime());
                    tempWorkLeave.setEndDateTime(utcZonedEnd.toLocalDateTime());
                    workLeavesTZ.add(tempWorkLeave);
                }

                return workLeavesTZ;
            }
            throw new RegionNotFoundException(regionId);
        }
    }

    @GetMapping("/activated-resources/{regionId}/work-leaves/{resourceId}")
    WorkLeave getWorkLeaveOfResource(@PathVariable("regionId") int regionId, @PathVariable("resourceId") int resourceId){
        WorkLeave workLeave = workLeaveRepository.findByRegionIdAndResourceId(regionId, resourceId);
        return workLeave;
    }

    @PostMapping("/activated-resources/{regionId}/work-leaves")
    void addWorkLeave(@PathVariable("regionId") int regionId, @Validated @RequestBody WorkLeave workLeave){
        workLeaveRepository.save(workLeave);
        LocalDate date = workLeave.getStartDateTime().toLocalDate();
        ResourceAvailability resourceAvailability = resourceAvailabilityRepository.findByResourceIdAndWorkingDay(workLeave.getResourceId(), date);
        if (resourceAvailability != null) {
            resourceAvailability.setOnLeave(true);
        }else{
            throw new ResourceAvailabilityNotFoundException();
        }
    }

    @DeleteMapping("/activated-resources/{regionId}/work-leaves/{resourceId}")
    void deleteWorkLeave(@PathVariable("regionId") int regionId, @PathVariable("resourceId") int resourceId, @RequestParam LocalDate dateOfLeave){
        ResourceAvailability resourceAvailability = resourceAvailabilityRepository.findByResourceIdAndWorkingDay(resourceId, dateOfLeave);
        if (resourceAvailability != null) {
            resourceAvailability.setOnLeave(true);
        }else{
            throw new ResourceAvailabilityNotFoundException();
        }
        workLeaveRepository.deleteByRegionIdAndResourceId(regionId, resourceId);
        resourceAvailability.setOnLeave(false);
    }

    @GetMapping("/activated-resources/{regionId}/bookings")
    List<Booking> getAllBookings(@PathVariable("regionId") int regionId){
        List<Booking> booking = bookingRepository.findAllByRegionId(regionId);
        if(booking.isEmpty()){
            throw new NoDataFoundException();
        }else{
            return booking;
        }
    }

    @GetMapping("/activated-resources/{regionId}/bookings/{resourceId}")
    List<Booking> getBookingOfResource(@PathVariable("regionId") int regionId, @PathVariable("resourceId") int resourceId){
        return bookingRepository.findAllByResourceId(resourceId);
    }

    @GetMapping("/activated-resources/{regionId}/bookings/{id}/booking-status")
    boolean getBookingStatusById(@PathVariable("regionId") int regionId, @PathVariable("id") int id){
        return bookingRepository.findById(id).getBookingStatus();
    }

    @GetMapping("/activated-resources/{regionId}/bookings/{id}")
    Booking getBookingInfoById(@PathVariable("regionId") int regionId, @PathVariable("id") int id){
        return bookingRepository.findById(id);
    }

    @PostMapping("/activated-resources/{regionId}/bookings/{id}/booking-status")
    void changeBookingStatus(@PathVariable("regionId") int regionId, @PathVariable("id") int id, @RequestParam boolean status){
        Booking bookingToChange = bookingRepository.findById(id);
        ClientLGProgression lgProgression = clientLGProgressionRepository.findByClientIdAndLearningSessionId(bookingToChange.getClientId(), bookingToChange.getLearningSessionId());
        if (status){
            bookingToChange.setBookingStatusTrue();
            lgProgression.setFinished(true);
        }
        else{
            bookingToChange.setBookingStatusFalse();
            lgProgression.setFinished(false);
        }
    }

    /*
    startDateTime and endDateTime include the pre and post LS durations, sessionStartTime and sessionEndTime don't.
     */

    @PostMapping("/activated-resources/{regionId}/bookings")
    int createBooking(@RequestBody Booking booking){
        ZonedDateTime ldtZoned = booking.getStartDateTime().atZone(ZoneId.systemDefault());
        ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
        booking.setStartDateTime(utcZoned.toLocalDateTime());

        ZonedDateTime ldtZoned2 = booking.getEndDateTime().atZone(ZoneId.systemDefault());
        ZonedDateTime utcZoned2 = ldtZoned2.withZoneSameInstant(ZoneId.of("UTC"));
        booking.setEndDateTime(utcZoned2.toLocalDateTime());

        booking.setSessionStartTime(booking.getSessionStartTime().atOffset(ZoneOffset.UTC).toLocalTime());
        booking.setSessionEndTime(booking.getSessionEndTime().atOffset(ZoneOffset.UTC).toLocalTime());

        bookingRepository.save(booking);

        ClientLGProgression lgProgression = new ClientLGProgression(booking.getClientId(), booking.getLearningGroupId() ,booking.getLearningSessionId());
        lgProgression.setFinished(true);
        clientLGProgressionRepository.save(lgProgression);

        return booking.getId();
    }

}
