package gov.kotkov.mikhail.exercise3.employee;

import static org.junit.Assert.*;
import static java.math.BigDecimal.valueOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import gov.kotkov.mikhail.exercise3.employee.Employee;
import gov.kotkov.mikhail.exercise3.employee.Programmer;

public class EmployeeTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void testCorrectWorkhoursPercentage() {
		Programmer.setStandardWorkhours(160);
		Employee e = new Programmer("Mike", "Gwar", valueOf(500));
		e.setActualWorkhours(140);
		assertEquals(87.5 , e.getWorkhoursPercentage(), 2);
	}
	
	@Test
	public void testEmptyName() {
		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("Employee must have first and last name");
		new Programmer("", "Gwar", valueOf(500));
	}
	
	@Test
	public void testNonAlphabeticName() {
		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("Name must consist of alphabetic characters");
		new Programmer("1St", "Gwar", valueOf(500));
	}
	
	@Test
	public void testNegativeWageRate() {
		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("wage rate can't be negative");
		Employee e = new Programmer("Mike", "Gwar", valueOf(500));
		e.setWageRate(valueOf(-1));
	}
	
	@Test
	public void testNegativeActualWorkhours() {
		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("actual workhours can't be negative");
	    Employee e = new Programmer("Mike", "Gwar", valueOf(500));
		e.setActualWorkhours(-1);
	}
	
	@Test
	public void testNegativeStandardWorkhours() {
		exceptionRule.expect(IllegalArgumentException.class);
	    exceptionRule.expectMessage("standard workhours can't be negative");
		Programmer.setStandardWorkhours(-1);
	}

}
