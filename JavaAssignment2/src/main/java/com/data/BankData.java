package com.data;

import com.opencsv.bean.CsvBindByPosition;

public class BankData {

	@CsvBindByPosition(position = 0)
	public String Emp_id;

	@CsvBindByPosition(position = 1)
	public String Emp_name;

	@CsvBindByPosition(position = 2)
	public String SalaryCredited;

	public String getEmp_id() {
		return Emp_id;
	}

	public String getSalary() {
		return SalaryCredited;
	}

	public String getEmp_name() {
		return Emp_name;
	}

}