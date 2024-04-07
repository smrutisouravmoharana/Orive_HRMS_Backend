package com.orivesolutions.hrms.interviewscheduler.controller;

import com.orivesolutions.hrms.interviewscheduler.dto.CandidateDto;
import com.orivesolutions.hrms.interviewscheduler.service.CandidateService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/candidates")
@CrossOrigin(origins = "*")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping(value = "/create/candidates", consumes = "multipart/form-data")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> savePoliciesEntity(
    		 @RequestParam String name,
    		 @RequestParam String address,
    		 @RequestParam  String email,
    		 @RequestParam String mobile,
    		 @RequestParam Integer ctc,
    		 @RequestParam Integer ectc,
    		 @RequestParam String location,
    		 @RequestParam String notice,
    		 @RequestParam(value = "file") MultipartFile file){
    	
    	String result = candidateService.saveCandidate( 
    			name, address, email, mobile, ctc, ectc, location, notice, file );
    
    	if(result != null) {
    		 return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to save candidate entity", HttpStatus.INTERNAL_SERVER_ERROR);
       
    	}
    }
    
    
    @GetMapping("/download/{id}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<byte[]> downloadsPdf(@PathVariable Long id) {
        byte[] pdf = candidateService.downloadPdf(id);

        if (pdf != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(id + "_policies.pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    		

    @GetMapping("/getCandidate/{email}")
 // @PreAuthorize("hasRole('client_HR')")
    public HttpEntity<CandidateDto> getCandidate(@PathVariable String email) {
        CandidateDto candidate = candidateService.getCandidate(email);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }
    
    @GetMapping("/getAllCandidate")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<CandidateDto>> getAllCandidates() {
        List<CandidateDto> candidates = candidateService.getAllCandidates();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }
}
