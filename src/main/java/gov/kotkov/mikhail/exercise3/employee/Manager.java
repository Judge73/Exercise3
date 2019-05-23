package gov.kotkov.mikhail.exercise3.employee;

import java.math.BigDecimal;

import gov.kotkov.mikhail.exercise3.util.Job;

public class Manager extends Employee {

	public Manager(String firstName, String lastName, BigDecimal wageRate) {
		super(firstName, lastName , Job.MANAGER, wageRate);
	}

}
