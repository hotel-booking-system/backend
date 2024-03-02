package br.com.bb.bugsandbytes.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotNullAndNotEmptyValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullAndNotEmpty {

	String message() default "The field is mandatory.";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}

