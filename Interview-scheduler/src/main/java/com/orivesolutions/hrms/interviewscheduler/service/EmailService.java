package com.orivesolutions.hrms.interviewscheduler.service;

import com.orivesolutions.hrms.interviewscheduler.dto.CalenderDto;
import com.orivesolutions.hrms.interviewscheduler.dto.EmailDto;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {

    void sendEmail(EmailDto emailDto);
    void sendCalenderInvite(CalenderDto calenderDto) throws IOException, MessagingException;
}
