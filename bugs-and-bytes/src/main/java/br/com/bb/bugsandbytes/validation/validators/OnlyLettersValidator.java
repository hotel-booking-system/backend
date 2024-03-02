package br.com.bb.bugsandbytes.validation.validators;

import br.com.bb.bugsandbytes.validation.annotations.OnlyLetters;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OnlyLettersValidator implements ConstraintValidator<OnlyLetters, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && value.matches("[a-zA-Z]+");
	}
}
