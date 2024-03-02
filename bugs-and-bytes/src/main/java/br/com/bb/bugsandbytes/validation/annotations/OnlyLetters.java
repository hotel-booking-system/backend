package br.com.bb.bugsandbytes.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OnlyLettersValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyLetters {

	String message() default "O campo deve ter apenas letras.";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
