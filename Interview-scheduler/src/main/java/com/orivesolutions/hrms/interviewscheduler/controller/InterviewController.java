package com.orivesolutions.hrms.interviewscheduler.controller;

import com.orivesolutions.hrms.interviewscheduler.dto.InterviewDto;
import com.orivesolutions.hrms.interviewscheduler.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/interviews")
@CrossOrigin(origins = "*")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping("/scheduleInterview")
 // @PreAuthorize("hasRole('client_HR')")
    public HttpEntity<InterviewDto> scheduleInterview(@RequestBody InterviewDto interviewDto) {
        interviewDto = interviewService.scheduleInterview(interviewDto);
        return new ResponseEntity<>(interviewDto, HttpStatus.CREATED);
    }

    @GetMapping("/getInterview")
 // @PreAuthorize("hasRole('client_HR')")
    public HttpEntity<List<InterviewDto>> getInterview(@PathVariable String schedulerEmail) {
        List<InterviewDto> interviews = interviewService.getInterview(schedulerEmail);
        return new ResponseEntity<>(interviews, HttpStatus.OK);
    }
    
    @GetMapping("/all")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<InterviewDto>> getAllInterviews() {
        List<InterviewDto> interviews = interviewService.getAllInterviews();
        return new ResponseEntity<>(interviews, HttpStatus.OK);
    }
}
