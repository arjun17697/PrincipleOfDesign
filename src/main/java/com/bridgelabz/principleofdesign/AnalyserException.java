package com.bridgelabz.principleofdesign;

public class AnalyserException extends Exception {

	public enum ExceptionType {
		INVALID_FILE_PATH, INVALID_CLASS_TYPE, INVALID_DELIMITER
	}


	public ExceptionType type;

	public AnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}