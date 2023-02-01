package com.devsuperior.bds02.controllers.exceptions;

import java.io.Serializable;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.DataBaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourcesExceptionsHandler implements Serializable {
	private static final long serialVersionUID = 1L;



	@ExceptionHandler(ResourceNotFoundException.class) //identifica método que intercepta a exception desse tipo com a exceção correspondente
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND; //tipo ENUN de HTTP (NOT_FOUND = 404)
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value()); 
		err.setError("Resource Not Found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		//status define o status da requisição
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataBaseException.class) //identifica método que intercepta a exception desse tipo com a exceção correspondente
	public ResponseEntity<StandardError> databaseError(DataBaseException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST; //tipo ENUN de HTTP (erro sem especificação = 400)
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value()); 
		err.setError("DataBase Exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		//status define o status da requisição
		return ResponseEntity.status(status).body(err);
	}

	
}
