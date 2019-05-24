package gov.kotkov.mikhail.exercise3.employee;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Programmer extends Employee {

	public Programmer(String firstName, String lastName, BigDecimal wageRate) {
		super(firstName, lastName, wageRate);
	}

	@Override
	public BigDecimal calculateSalary() {
		return getWageRate().multiply(new BigDecimal(
				getWorkhoursPercentage()/100)).setScale(2, RoundingMode.HALF_UP);
	}

}
