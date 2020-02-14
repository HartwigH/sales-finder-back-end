package com.sf.exceptions;

import com.sf.exceptions.TmsException;

public class ServiceException extends TmsException {
	
	public ServiceException(String message){
		super(message);
	}
	
	public ServiceException(Exception e){
		super(e);
	}
}
