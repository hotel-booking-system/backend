package br.com.bb.bugsandbytes.validation.annotations;

import br.com.bb.bugsandbytes.validation.validators.MinLengthValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinLengthValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinLength {

	String message() default "The field must have at least 3 characters.";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};

	int value() default 3;

}

