package com.teamseven.hr.statistics;

import com.seventeam.hr.domain.Department;

public class DepartmentStatistics extends Department {

	double salary;
	private int count;
	private String name;
	private double maxSalary;
	private double minSalary;
	private double avgSalary;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}
	public double getAvgSalary() {
		return avgSalary;
	}
	public void setAvgSalary(double avgSalary) {
		this.avgSalary = avgSalary;
	}

}
