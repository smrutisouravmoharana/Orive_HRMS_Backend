package com.orive.bank.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orive.bank.dto.EventDto;
import com.orive.bank.service.EventService;


//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "event")
@CrossOrigin(origins = "*")
public class EventController {
	
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	private EventService eventService;
	
	// Create a new Event
    @PostMapping("/create/event")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
    	EventDto createdEvent = eventService.createEvent(eventDto);
        logger.info("Created Event with name: {}", createdEvent.getTitle());
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    // Get all Event      
    @GetMapping("/get/event")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<EventDto>> getAllEvent() {
        List<EventDto> event = eventService.getAllEvent();
        logger.info("Retrieved {} Event from the database", event.size());
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    // Get Event by ID
    @GetMapping("/get/{eventId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<EventDto> getEventById(@PathVariable String eventId) {
        Optional<EventDto> event = eventService.getEventById(eventId);
        if (event.isPresent()) {
            logger.info("Retrieved Event with ID: {}", eventId);
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        } else {
            logger.warn("Event with ID {} not found", eventId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Event by ID
    @PutMapping("/update/{eventId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<EventDto> updateEvent(@PathVariable String eventId, @RequestBody EventDto updatedEventDto) {
    	EventDto updatedEvent = eventService.updateEvent(eventId, updatedEventDto);
        if (updatedEvent != null) {
            logger.info("Updated Event with ID: {}", eventId);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            logger.warn("Event with ID {} not found for update", eventId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete Event by ID
    @DeleteMapping("/delete/{eventId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventId) {
    	eventService.deleteEvent(eventId);
        logger.info("Deleted Event with ID: {}", eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
	    @GetMapping("/count/event")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countEvent()
	    {
	    	return eventService.countEvent();
	    }

}
