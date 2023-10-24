package com.userservice.Microservices.services.payload;

import org.springframework.http.HttpStatus;



import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
	private String message;
	private Boolean success;
	private HttpStatus status;
	
	
	

}
