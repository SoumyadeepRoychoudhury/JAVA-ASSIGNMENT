package com.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.data.BankData;
import com.data.ClientData;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;


public class MatchandMismatches {
	
	public static void main(String[] args)
			throws IOException {
		List<BankData> BankData;
		List<ClientData> ClientData;

		try {
			//Reading BankData.csv file using openCSV parser
			BankData = new CsvToBeanBuilder<BankData>(
					new FileReader("../JavaAssignment2/Resources/Bank.csv")).withType(BankData.class)
							.withSkipLines(1).build().parse();

			//Reading ClientData.csv file using openCSV parser
			ClientData = new CsvToBeanBuilder<ClientData>(
					new FileReader("../JavaAssignment2/Resources/EmployeeDetails.csv"))
							.withType(ClientData.class).withSkipLines(1).build().parse();

			//List of String Array 
			List<String[]> comparisonData = new ArrayList<String[]>();
			
			//writing matched and mismatched data in ComparisonData.csv
			Writer outputFile = new FileWriter("ComparisonData.csv");
			CSVWriter writer = new CSVWriter(outputFile);
			String[] header = { "EmpId", "EmpName" , "Salary", "SalaryCredited", "SalarayDeficit", "SalarySurplus"};
			comparisonData.add(header);
			
			//checking if the salary credited is deficit or surplus with respect to the actual salary
			for (int i = 0; i < ClientData.size(); i++) {
				int salaryDeficit = 0;
				int salarySurplus = 0;
				if (Integer.parseInt(ClientData.get(i).getSalary()) > Integer.parseInt(BankData.get(i).getSalary())) {
					salaryDeficit = Integer.parseInt(ClientData.get(i).getSalary())
							- Integer.parseInt(BankData.get(i).getSalary());
					salarySurplus = 0;
				} else if (Integer.parseInt(ClientData.get(i).getSalary()) < Integer
						.parseInt(BankData.get(i).getSalary())) {
					salarySurplus = -Integer.parseInt(ClientData.get(i).getSalary())
							+ Integer.parseInt(BankData.get(i).getSalary());
					salaryDeficit = 0;
				}

				//adding the compared data to list of string array
				comparisonData.add(new String[] { 
						BankData.get(i).getEmp_id(), 
						BankData.get(i).getEmp_name(),
						ClientData.get(i).getSalary(), 
						BankData.get(i).getSalary(), 
						Integer.toString(salaryDeficit),
						Integer.toString(salarySurplus) 
						});

			}
			writer.writeAll(comparisonData);
			writer.close();

			System.out.println("Successfully created match vs mismatch data in Comparison.csv file. Refresh the Project file if not vissible.");

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}

	}
}