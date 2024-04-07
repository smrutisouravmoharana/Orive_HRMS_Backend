package com.orivesolutions.hrms.interviewscheduler.domain;

import com.orivesolutions.hrms.interviewscheduler.config.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@Entity
@Table(name = "candidate")
@Data
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    //@Convert(converter = AesEncryptor.class)
    private String name;
    
    @Column(name = "address")
    //@Convert(converter = AesEncryptor.class)
    private String address;
    
    @Column(name = "emailId", unique = true, nullable = false)
   // @Convert(converter = AesEncryptor.class)
    private String email;
    
    @Column(name = "mobile")
    //@Convert(converter = AesEncryptor.class)
    private String mobile;
    
    @Column(name = "ctc")
   // @Convert(converter = AesEncryptor.class)
    private Integer ctc;
    
    @Column(name = "ectc")
    //@Convert(converter = AesEncryptor.class)
    private Integer ectc;
    
    @Column(name = "location")
    //@Convert(converter = AesEncryptor.class)
    private String location;
    
    @Column(name = "notice")
    @Convert(converter = AesEncryptor.class)
    private String notice;
    
    @Lob
    @Column(name = "resume_url",length = 100000)
    private byte[] resumeUrl;
}
