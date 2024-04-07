package com.orive.orive_website.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReachUsDto {

    private Long reachUsId;
	private String name;
	private String email;
	private Long phoneNumber;
	private String selectService;
	private String message;
}
