package com.gl.validator;

import java.util.Map;

public class ValidationOutput {
	
	private Boolean isValid;
	
	private Map<String,String> invalidFields;
	
	public ValidationOutput() {
		
	}
	
	public ValidationOutput(Boolean isValid, Map<String, String> invalidFields) {
		this.isValid = isValid;
		this.invalidFields = invalidFields;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public Map<String, String> getInvalidFields() {
		return invalidFields;
	}
	public void setInvalidFields(Map<String, String> invalidFields) {
		this.invalidFields = invalidFields;
	}
	@Override
	public String toString() {
		return "ValidationOutput [isValid=" + isValid + ", invalidFields=" + invalidFields + "]";
	}
	
	
}
