package edu.ucam.cliente.service;

import edu.ucam.cliente.enums.ResponseStatus;
import edu.ucam.cliente.exceptions.NullResponseException;

public class ResponseParser {
	
	private ResponseStatus status;
	private int num;
	private int codeResponse;
	private String msg;
	private int port;
	private String ip;
	
	
	public ResponseParser(String response) throws NullResponseException{
		String parts[];
		
		StringBuilder msgBuilder = new StringBuilder();
		
		if (response == null || response.trim().isEmpty()) {
			throw new NullResponseException("Respues del servidor vacía");
		}
		
		parts = response.split(" ");
		
		if(parts.length >= 4) {
			
			for (int i = 3; i < parts.length; i++) {
				msgBuilder.append(parts[i]).append(" ");
			}
		
			this.status = ResponseStatus.valueOf(parts[0]);
		    this.num = 	Integer.parseInt(parts[1]);
			this.codeResponse= Integer.parseInt(parts[2]);
			this.msg = msgBuilder.toString().trim();
			
			if (response.startsWith("PREOK")) {
				this.ip =  parts[4];
				this.port =  Integer.parseInt(parts[3]);
			}
			
		}
		
	}
	
	public boolean isSuccess() {
		return ResponseStatus.OK == status;
	}
	
	public boolean isPREOK() {
		return ResponseStatus.PREOK == status;
	}
	
	public String getMessage() {
		return this.msg;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public String getIp() {
		return this.ip;
	}
	

}
