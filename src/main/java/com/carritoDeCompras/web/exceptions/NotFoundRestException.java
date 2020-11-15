package com.carritoDeCompras.web.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.carritoDeCompras.web.errors.FieldErrorVM;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundRestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String message;
	private final String description;

	private List<FieldErrorVM> fieldErrors;

	public NotFoundRestException(String message) {
		this(message, null);
	}

	public NotFoundRestException(String message, String description) {
		this.message = message;
		this.description = description;
	}
	
	public NotFoundRestException(String message, String description, List<FieldErrorVM> fieldErrors) {
        this.message = message;
        this.description = description;
        this.fieldErrors = fieldErrors;
    }

    public void add(String objectName, String field, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorVM(objectName, field, message));
    }

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}

	public List<FieldErrorVM> getFieldErrors() {
		return fieldErrors;
	}
}