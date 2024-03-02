package br.com.bb.bugsandbytes.validation.annotations;

import br.com.bb.bugsandbytes.validation.validators.StrongPasswordValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
	String message() default "A senha deve ter no mínimo 8 caracteres, com pelo menos uma letra maiúscula, uma minúscula, um número e um caracter especial.";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};

}
