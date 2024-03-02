package br.com.bb.bugsandbytes.validation.validators;

import br.com.bb.bugsandbytes.validation.annotations.NotNullAndNotEmpty;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotNullAndNotEmptyValidatorTest {

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();

	@Test
	void testNullValue() {
		DummyClass obj = new DummyClass(null);
		Set<ConstraintViolation<DummyClass>> violations = validator.validate(obj);

		assertEquals(1, violations.size());
		assertEquals("The field is mandatory.", violations.iterator().next().getMessage());
	}

	@Test
	void testEmptyStringValue() {
		DummyClass obj = new DummyClass("");
		Set<ConstraintViolation<DummyClass>> violations = validator.validate(obj);

		assertEquals(1, violations.size());
		assertEquals("The field is mandatory.", violations.iterator().next().getMessage());
	}

	@Test
	void testValidValue() {
		DummyClass obj = new DummyClass("Valid Value");
		Set<ConstraintViolation<DummyClass>> violations = validator.validate(obj);

		assertTrue(violations.isEmpty());
	}

	private static class DummyClass {
		@NotNullAndNotEmpty
		private final String field;

		public DummyClass(String field) {
			this.field = field;
		}
	}

}