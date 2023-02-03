package com.devsuperior.bds02.services.exceptions;

import java.io.Serializable;

public class DataBaseException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public DataBaseException(String msg) {
		super(msg);
	}
}
