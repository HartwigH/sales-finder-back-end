package com.sf.exceptions;

public class UserException  extends TmsException{

	public UserException(String message){
		super(message);
	}
	
	public UserException(Exception e){
		super(e);
	}
}
