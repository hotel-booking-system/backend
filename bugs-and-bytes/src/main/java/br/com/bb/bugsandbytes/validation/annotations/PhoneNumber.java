package br.com.bb.bugsandbytes.validation.annotations;

import br.com.bb.bugsandbytes.validation.validators.PhoneNumberValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
	String message() default "O campo 'phoneNumber' é inválido.";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};

}
