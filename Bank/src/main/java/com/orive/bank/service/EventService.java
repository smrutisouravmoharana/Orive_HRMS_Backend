package com.orive.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.bank.dto.EventDto;
import com.orive.bank.entities.EventEntity;
import com.orive.bank.repository.EventRepository;



@Service
public class EventService {
	
	private static final Logger logger = LoggerFactory.getLogger(EventService.class);

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public EventDto createEvent(EventDto eventDto) {
    	EventEntity eventEntity = convertToEntity(eventDto);
    	EventEntity savedEvent = eventRepository.save(eventEntity);
        logger.info("Created Event with ID: {}", savedEvent.getEventId());
        return convertToDTO(savedEvent);
    }

    // Read
    public List<EventDto> getAllEvent() {
        List<EventEntity> eventEntities = eventRepository.findAll();
        logger.info("Retrieved {} Event from the database", eventEntities.size());
        return eventEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by EventId
    public Optional<EventDto> getEventById(String eventId) {
        Optional<EventEntity> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            return Optional.of(convertToDTO(event.get()));
        } else {
            logger.warn("Event with ID {} not found", eventId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public EventDto updateEvent(String eventId, EventDto eventDto) {
        Optional<EventEntity> existingEventOptional = eventRepository.findById(eventId);
        if (existingEventOptional.isPresent()) {
        	EventEntity existingEvent = existingEventOptional.get();
        	existingEvent.setTitle(eventDto.getTitle());
//        	existingEvent.setStatus(eventDto.getStatus());
        	modelMapper.map(eventDto, existingEventOptional);
            EventEntity updatedEvent = eventRepository.save(existingEvent);
            logger.info("Updated Event with ID: {}", updatedEvent.getEventId());
            return convertToDTO(updatedEvent);
        } else {
            logger.warn("Event with ID {} not found for update", eventId);
            return null;
        }
    }
    
    // Delete
    public void deleteEvent(String eventId) {
    	eventRepository.deleteById(eventId);
        logger.info("Deleted Event with ID: {}", eventId);
    }

    //count the total Event
    public long countEvent()
	 {
		 return eventRepository.count();
	 }
    
	// Helper method to convert EventDto to EventEntity
    private EventEntity convertToEntity(EventDto eventDto)
    {
    	return modelMapper.map(eventDto, EventEntity.class);
    }

    // Helper method to convert EventEntity to EventDto
    private EventDto convertToDTO(EventEntity eventEntity) {
        return modelMapper.map(eventEntity, EventDto.class);
    } 
}
