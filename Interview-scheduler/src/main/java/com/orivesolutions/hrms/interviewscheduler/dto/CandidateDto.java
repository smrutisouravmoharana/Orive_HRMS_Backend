package com.orivesolutions.hrms.interviewscheduler.dto;



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
@Data
public class CandidateDto {

    private Long id;
    private String name;
    private String address;
    private String email;
    private String mobile;
    private Integer ctc;
    private Integer ectc;
    private String location;
    private String notice;
    private byte[] resumeUrl;
}
