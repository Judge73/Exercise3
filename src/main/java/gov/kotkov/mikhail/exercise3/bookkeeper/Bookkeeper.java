package gov.kotkov.mikhail.exercise3.bookkeeper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gov.kotkov.mikhail.exercise3.employee.Employee;

import java.util.function.*;

class Bookkeeper {

	public Map<Employee, BigDecimal> calculateSalariesForEmployees(List<Employee> employees) {
		return employees.parallelStream().collect(Collectors.toMap(Function.identity(), Employee::calculateSalary));
	}
	
}
