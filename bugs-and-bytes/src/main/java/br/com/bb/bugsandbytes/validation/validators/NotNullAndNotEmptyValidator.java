package br.com.bb.bugsandbytes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotNullAndNotEmptyValidator implements ConstraintValidator<NotNullAndNotEmpty, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && !value.isEmpty();
	}

}
