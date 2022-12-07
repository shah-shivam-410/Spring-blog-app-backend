package com.example.demo.payloads;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

public class APIResponses {

	private String message;
	private boolean success;
	private LocalDateTime timestamp;
	
	public APIResponses(String message, boolean success, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.success = success;
		this.timestamp = timestamp;
	}
	
	public APIResponses() {}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
