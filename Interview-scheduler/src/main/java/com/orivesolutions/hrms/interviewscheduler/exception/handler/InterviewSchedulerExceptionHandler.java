package com.orivesolutions.hrms.interviewscheduler.exception.handler;

import com.orivesolutions.hrms.interviewscheduler.dto.ResponseDto;
import com.orivesolutions.hrms.interviewscheduler.exception.ResourceNotFoundException;
import com.orivesolutions.hrms.interviewscheduler.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class InterviewSchedulerExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ResponseDto> notFoundException(ResourceNotFoundException notFoundException) {
        log.info( "No resource found");
        return new ResponseEntity<>( ResponseUtil.getFailureResponse( notFoundException.getMessage() ), notFoundException.getCode() );
    }


}
