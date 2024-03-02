package br.com.bb.bugsandbytes.validation.annotations;

import br.com.bb.bugsandbytes.validation.validators.MinLengthValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinLengthValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinLength {
	int minLength() default 3;

	String message() default "O campo deve ter no mínimo ${minLength} caracteres.";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
