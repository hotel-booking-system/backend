package br.com.bb.bugsandbytes.validation.annotations;

import br.com.bb.bugsandbytes.validation.validators.OnlyLettersValidator;
import br.com.bb.bugsandbytes.validation.validators.OnlyNumbersValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OnlyNumbersValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyNumbers {
	String message() default "O campo deve conter apenas números.";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
