package com.data;

import com.opencsv.bean.CsvBindByPosition;

public class ClientData {

	@CsvBindByPosition(position = 0)
	private String empId;

	@CsvBindByPosition(position = 1)
	private String empName;

	@CsvBindByPosition(position = 2)
	private String salary;

	public String getEmpId() {
		return empId;
	}

	public String getEmpName() {
		return empName;
	}

	public String getSalary() {
		return salary;
	}

}