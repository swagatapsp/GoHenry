package com.gohenry.parentapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 8251851744700257233L;
	
	public PersonNotFoundException() {
        super();
    }
    public PersonNotFoundException(String s) {
        super(s);
    }
    public PersonNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public PersonNotFoundException(Throwable throwable) {
        super(throwable);
    }
  }
