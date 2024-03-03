package br.com.bb.bugsandbytes.user.service;

import br.com.bb.bugsandbytes.exception.erros.ApiBadRequestException;
import br.com.bb.bugsandbytes.user.domain.dto.UserRequest;

public class UserValidator {

	public static void validateCreateUserRequest(UserRequest userRequest) throws ApiBadRequestException {
		if (isEmpty(userRequest.getName()) || userRequest.getName().length() < 3) {
			throw new ApiBadRequestException("Name is required and must have at least 3 characters.");
		}

		if (isEmpty(userRequest.getUsername()) || isEmpty(userRequest.getPassword())
				|| isEmpty(userRequest.getPhoneNumber())) {
			throw new ApiBadRequestException("Username, password and phone number are required fields.");
		}

		if (!isValidPassword(userRequest.getPassword())) {
			throw new ApiBadRequestException("Password must contain at least 8 characters, 1 lowercase letter, " +
					"1 uppercase letter, 1 number and a special character.");
		}

		if (!isValidPhoneNumber(userRequest.getPhoneNumber())) {
			throw new ApiBadRequestException("Invalid phone number format. Must start with + and be 5 to 15 digits long.");
		}
	}

	private static boolean isEmpty(String value) {
		return value == null || value.isBlank();
	}

	private static boolean isValidPassword(String password) {
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
		return password.matches(regex);
	}

	private static boolean isValidPhoneNumber(String phoneNumber) {
		String regex = "^\\+?[1-9]\\d{4,14}$";
		return phoneNumber.matches(regex);
	}
}
