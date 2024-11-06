package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import entities.enums.WorkerLevel;

public class WorkerOperations {

	private Scanner sc;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public WorkerOperations(Scanner sc) {
		this.sc = sc;
	}

	public void run() throws ParseException {
		Locale.setDefault(Locale.US);

		String departmentName = "";
		String workerName = "";

		// Loop para garantir que o nome do departamento seja inserido em maiúsculas
		while (true) {
			System.out.print("Enter department's name (uppercase): ");
			departmentName = sc.nextLine();

			if (departmentName.equals(departmentName.toUpperCase())) { // Verifica se está em maiúsculas
				break; // Sai do loop se a condição for atendida
			} else {
				System.out.println("Invalid input. Please enter the department's name in uppercase.");
			}
		}

		while (true) {
			System.out.print("Enter worker data: ");
			System.out.print("Name (max 50 characters): ");
			try {
				workerName = sc.nextLine();
				if (workerName.length() > 50) {
					throw new IllegalArgumentException("Name cannot exceed 50 characters.");
				}
				break; // Sai do loop se o nome for válido
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage()); // Exibe a mensagem de erro
			}
		}

		WorkerLevel workerLevel = null;
		while (workerLevel == null) {
			System.out.print("Level (JUNIOR, MID_LEVEL, SENIOR): ");
			String inputLevel = sc.nextLine().toUpperCase(); // Converte para maiúsculas para corresponder ao enum

			try {
				workerLevel = WorkerLevel.valueOf(inputLevel); // Tenta converter a string para o enum
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid input. Please enter a valid level (JUNIOR, MID_LEVEL, SENIOR).");
			}
		}

		double baseSalary = 0.0;
		while (true) {
			System.out.print("Base Salary: ");
			try {
				baseSalary = Double.parseDouble(sc.nextLine().replace(",", ".")); // Substitui vírgulas por pontos
				break; // Sai do loop se a conversão for bem-sucedida
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number for base salary.");
			}
		}

		Department department = new Department(departmentName);
		Worker worker = new Worker(workerName, workerLevel, baseSalary, department);

		int number = 0;
		while (true) {
			System.out.print("How many contracts to this worker? ");
			try {
				number = sc.nextInt();
				if (number < 0) {
					throw new IllegalArgumentException("Number of contracts cannot be negative.");
				}
				sc.nextLine(); // Consumir a nova linha deixada pelo nextInt
				break; // Sai do loop se o número for válido
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage()); // Exibe a mensagem de erro
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid number.");
				sc.nextLine(); // Limpa o buffer do Scanner
			}
		}

		for (int i = 1; i <= number; i++) {
		    boolean validDate = false;
		    Date contractDate = null;

		    while (!validDate) {
		        try {
		            System.out.println("Enter contract #" + i + " data:");
		            System.out.print("Date (DD/MM/YYYY): ");
		            String dateInput = sc.next();

		            // Tenta converter a entrada em uma data válida
		            contractDate = sdf.parse(dateInput);
		            validDate = true; // Se não lançar exceção, a data é válida

		        } catch (ParseException e) {
		            System.out.println("Invalid date format. Please enter a valid date in the format DD/MM/YYYY.");
		        }
		    }

		    System.out.print("Value per hour: ");
		    double valuePerHour = sc.nextDouble();
		    System.out.print("Duration (hours): ");
		    int hours = sc.nextInt();

		    HourContract hourContract = new HourContract(contractDate, valuePerHour, hours);
		    worker.addContract(hourContract);
		}
		System.out.println();
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		String monthAndYear = sc.next();

		int month = 0;
		int year = 0;

		try {
			// Verificando se o formato do mês e ano está correto
			if (monthAndYear.length() != 7 || monthAndYear.charAt(2) != '/') {
				throw new IllegalArgumentException("Invalid format. Please use MM/YYYY.");
			}

			month = Integer.parseInt(monthAndYear.substring(0, 2));
			year = Integer.parseInt(monthAndYear.substring(3));

			// Verifica se o mês e o ano são válidos
			if (month < 1 || month > 12) {
				throw new IllegalArgumentException("Invalid month. Please enter a month between 01 and 12.");
			}

			if (year < 1900 || year > 9999) {
				throw new IllegalArgumentException("Invalid year. Please enter a year between 1900 and 9999.");
			}

		} catch (NumberFormatException e) {
			System.out.println("Invalid number format. Please ensure you enter valid integers for month and year.");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));

	}
}
