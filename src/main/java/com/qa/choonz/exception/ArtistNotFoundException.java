package com.qa.choonz.exception;

import javax.persistence.PersistenceException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Artist not found")
public class ArtistNotFoundException extends PersistenceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8447598503082115460L;



}
