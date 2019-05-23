package gov.kotkov.mikhail.exercise3.repository;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import gov.kotkov.mikhail.exercise3.employee.Employee;
import gov.kotkov.mikhail.exercise3.employee.Manager;
import gov.kotkov.mikhail.exercise3.employee.Programmer;
import gov.kotkov.mikhail.exercise3.repository.CSVRepository;

public class CSVRepositoryTest {

	@Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Test
	public void testReadWrite() {
		Programmer.setStandardWorkhours(160);
		Manager.setStandardWorkhours(150);
		Employee programmer1 = new Programmer("Jonathan", "Bill", valueOf(500));
		programmer1.setActualWorkhours(160);
		Employee programmer2 = new Programmer("Ruslan", "Petrov", valueOf(600));
		programmer2.setActualWorkhours(140);
		Employee manager1 = new Manager("Ivan", "Popov", valueOf(500));
		manager1.setActualWorkhours(150);
		CSVRepository repository = CSVRepository.getInstance();
		List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee[]{programmer1, programmer2, manager1}));
		File file = null;
		try {
			file = tempFolder.newFile("test.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		repository.setFile(file);
		repository.write(employees);
		System.out.print(tempFolder.getRoot());
		assertEquals(employees, repository.read());
	}
	
}
