package br.com.bb.bugsandbytes.validation.validators;

import br.com.bb.bugsandbytes.validation.annotations.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OnlyNumbersValidator implements ConstraintValidator<PhoneNumber, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && value.matches("[0-9]+");
	}
}
