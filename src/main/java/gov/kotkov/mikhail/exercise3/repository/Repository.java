package gov.kotkov.mikhail.exercise3.repository;

import java.util.List;

import gov.kotkov.mikhail.exercise3.employee.Employee;

public interface Repository {

	List<Employee> read();
	
	void write(List<Employee> employees);
}
