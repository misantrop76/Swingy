package fr.swingy.rpg.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtil
{
	private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static final Validator validator = factory.getValidator();
	public static <T> boolean validate(T object)
	{
		Set<ConstraintViolation<T>> violations = validator.validate(object);

		if (!violations.isEmpty())
		{
			for (ConstraintViolation<T> violation : violations)
				System.out.println(violation.getMessage());
			return false;
		}
		return true;
	}
}