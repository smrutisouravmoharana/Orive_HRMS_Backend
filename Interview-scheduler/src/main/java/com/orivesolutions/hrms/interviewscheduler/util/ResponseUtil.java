package com.orivesolutions.hrms.interviewscheduler.util;

import com.orivesolutions.hrms.interviewscheduler.dto.ResponseDto;
import com.orivesolutions.hrms.interviewscheduler.enums.ApiStatus;

import java.util.Collection;
import java.util.Collections;

public class ResponseUtil {



    public static ResponseDto getSuccessResponse(Object data) {
        ResponseDto response = new ResponseDto();
        response.setMessage("Data fetched Successfully");
        response.setStatusCode( ApiStatus.SUCCESS.getValue() );
        response.setData( Collections.singleton( data ) );
        return response;
    }



    public static ResponseDto getFailureResponse(String message) {
        ResponseDto response = new ResponseDto();
        response.setMessage( message );
        response.setStatusCode( ApiStatus.FAILURE.getValue() );
        return response;
    }

    public static ResponseDto getResponse(String message, String status, Collection data) {
        ResponseDto response = new ResponseDto();
        response.setMessage( message );
        response.setStatusCode( status );
        response.setData( data );
        return response;
    }

    public static ResponseDto getResponse(String message, String status, Object data) {
        ResponseDto response = new ResponseDto();
        response.setMessage( message );
        response.setStatusCode( status );
        response.setData( Collections.singleton( data ) );
        return response;
    }

}
