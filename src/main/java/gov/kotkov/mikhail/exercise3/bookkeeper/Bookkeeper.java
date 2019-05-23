package gov.kotkov.mikhail.exercise3.bookkeeper;

import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gov.kotkov.mikhail.exercise3.employee.Employee;

import java.util.function.*;

class Bookkeeper {

	public Map<Employee, BigDecimal> calculateSalariesForEmployees(List<Employee> employees) {
		return employees.parallelStream().collect(Collectors.toMap(Function.identity(), this::calculateSalary));
	}
	
	private BigDecimal calculateSalary(Employee employee) {
		BigDecimal wageRate = employee.getWageRate();
		switch(employee.getJob()) {
			case PROGRAMMER: return wageRate.multiply(valueOf(
					employee.getWorkhoursPercentage()/100)).setScale(2, RoundingMode.HALF_UP);
			case MANAGER: double ratio = employee.getWorkhoursPercentage()/100;
				return ratio > 1.0 ? wageRate 
						: wageRate.multiply(valueOf(ratio)).setScale(2, RoundingMode.HALF_UP);
			default: return null;
		}
	}
}
