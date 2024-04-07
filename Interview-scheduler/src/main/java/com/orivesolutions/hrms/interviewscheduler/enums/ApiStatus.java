package com.orivesolutions.hrms.interviewscheduler.enums;

public enum ApiStatus {
    SUCCESS( "Success" ),
    FAILURE( "Failure" );

    private String value;

    private ApiStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
