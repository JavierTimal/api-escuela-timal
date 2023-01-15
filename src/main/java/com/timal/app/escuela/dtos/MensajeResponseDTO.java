package com.timal.app.escuela.dtos;

public class MensajeResponseDTO {
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MensajeResponseDTO() {
		super();
	}
	public MensajeResponseDTO(String message) {
		super();
		this.message = message;
	}
	
	
}
