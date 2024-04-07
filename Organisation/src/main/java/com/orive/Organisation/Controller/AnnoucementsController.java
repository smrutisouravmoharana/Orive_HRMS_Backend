package com.orive.Organisation.Controller;

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

import com.orive.Organisation.Dto.AnnoucementDto;
import com.orive.Organisation.Service.AnnoucementService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "announcement")
@CrossOrigin(origins = "*")
public class AnnoucementsController {

	private static final Logger logger = LoggerFactory.getLogger(AnnoucementsController.class);

    @Autowired
    private AnnoucementService annoucementService;

  
  	// Create a new Annoucement
      @PostMapping("/create/announcement")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<AnnoucementDto> createAnnoucement(@RequestBody AnnoucementDto annoucementDto) {
    	  AnnoucementDto createdAnnoucement = annoucementService.createAnnouncements(annoucementDto);
          logger.info("Created Annoucement with name: {}", createdAnnoucement.getCompanyName());
          return new ResponseEntity<>(createdAnnoucement, HttpStatus.CREATED);
      }

      // Get all Annoucement     
      @GetMapping("/get/announcement")
   // @PreAuthorize("hasRole('client_HR')|| hasRole('client_Employee')")
      public ResponseEntity<List<AnnoucementDto>> getAllAnnoucement() {
          List<AnnoucementDto> announcement = annoucementService.getAllAnnouncements();
          logger.info("Retrieved {} Annoucement from the database", announcement.size());
          return new ResponseEntity<>(announcement, HttpStatus.OK);
      }

      // Get Annoucement by ID
      @GetMapping("/get/{announcementId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<AnnoucementDto> getAnnoucementById(@PathVariable Long announcementId) {
          Optional<AnnoucementDto> announcement = annoucementService.getAnnouncementsById(announcementId);
          if (announcement.isPresent()) {
              logger.info("Retrieved Annoucement with ID: {}", announcementId);
              return new ResponseEntity<>(announcement.get(), HttpStatus.OK);
          } else {
              logger.warn("Annoucement with ID {} not found", announcementId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }

      // Update Annoucement by ID
      @PutMapping("/update/{announcementId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<AnnoucementDto> updateAnnoucement(@PathVariable Long announcementId, @RequestBody AnnoucementDto updatedAnnoucementDTO) {
    	  AnnoucementDto updatedAnnoucement= annoucementService.updateAnnouncement(announcementId, updatedAnnoucementDTO);
          if (updatedAnnoucement != null) {
              logger.info("Updated Annoucement with ID: {}", announcementId);
              return new ResponseEntity<>(updatedAnnoucement, HttpStatus.OK);
          } else {
              logger.warn("Annoucement with ID {} not found for update", announcementId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      


      // Delete Annoucement by ID
      @DeleteMapping("/delete/{announcementId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteAnnoucement(@PathVariable Long announcementId) {
    	  annoucementService.deleteAnnouncement(announcementId);
          logger.info("Deleted Annoucement with ID: {}", announcementId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  	    
      //Count the total Annoucement
  	    @GetMapping("/count/announcement")
  	// @PreAuthorize("hasRole('client_HR')")
  	    public long countAnnoucement()
  	    {
  	    	return annoucementService.countAnnouncement();
  	    }
}
