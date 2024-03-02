package br.com.bb.bugsandbytes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OnlyLettersValidator implements ConstraintValidator<OnlyLetters, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && value.matches("[a-zA-Z]+");
	}
}
