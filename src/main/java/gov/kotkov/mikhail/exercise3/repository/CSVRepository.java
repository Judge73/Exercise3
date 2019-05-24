package gov.kotkov.mikhail.exercise3.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import gov.kotkov.mikhail.exercise3.employee.Employee;
import gov.kotkov.mikhail.exercise3.employee.Manager;
import gov.kotkov.mikhail.exercise3.employee.Programmer;

public class CSVRepository implements Repository{

	private File file = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + "employees.csv");
	
	private CSVRepository() {}
	
	private static class CSVRepositoryHolder {
		static final CSVRepository INSTANCE = new CSVRepository();
	}
	
	public static CSVRepository getInstance() {
		return CSVRepositoryHolder.INSTANCE;
	}
	
	public void setFilePath(String path) {
		file = new File(path);
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public void deleteCurrentFile() {
		file.delete();
	}
	
	@Override
	public void write(List<Employee> employees) {
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (file.isFile()) {
			try(BufferedWriter csvWriter = new BufferedWriter(new FileWriter(file))) {
				if(file.length() == 0) {
					csvWriter.append("firstName,");
					csvWriter.append("lastName,");
					csvWriter.append("wageRate,");
					csvWriter.append("actualWorkhours,");
					csvWriter.append("job");
					csvWriter.append(System.lineSeparator());
					csvWriter.flush();
				}
				for(Employee employee : employees) {
					csvWriter.append(employee.getFirstName() + ",");
					csvWriter.append(employee.getLastName() + ",");
					csvWriter.append(employee.getWageRate() + ",");
					csvWriter.append(employee.getActualWorkhours() + ",");
					csvWriter.append(employee.getClass().getSimpleName());
					csvWriter.append(System.lineSeparator());
				}
				csvWriter.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
		}
		
	}

	@Override
	public List<Employee> read() {
		List<Employee> employees = new ArrayList<>();
		if (file.isFile()) {
			try(BufferedReader csvReader = new BufferedReader(new FileReader(file))) {
				String line;
				//skip header
				csvReader.readLine();
				while((line = csvReader.readLine()) != null) {
					String[] data = line.split(",");
					Employee employee = null;
					switch(data[4]) {
						case "Programmer": employee = new Programmer(data[0], data[1], new BigDecimal(data[2]));
						break;
						case "Manager": employee = new Manager(data[0], data[1], new BigDecimal(data[2]));
						break;
					}
					employee.setActualWorkhours(Integer.parseInt(data[3]));
					employees.add(employee);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return employees;
	}
}
