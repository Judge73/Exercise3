package gov.kotkov.mikhail.exercise3.employee;

import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Manager extends Employee {

	public Manager(String firstName, String lastName, BigDecimal wageRate) {
		super(firstName, lastName , wageRate);
	}

	@Override
	public BigDecimal calculateSalary() {
		double ratio = getWorkhoursPercentage()/100;
		return ratio > 1.0 ? getWageRate() 
				: getWageRate().multiply(valueOf(ratio)).setScale(2, RoundingMode.HALF_UP);
	}

}
