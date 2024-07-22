package com.seventeam.hr.statistics;
import java.util.Date;

import com.seventeam.hr.domain.Emp;

public class EmpStatistics extends Emp {
	
	private int count ;
	double salary ;
	private double maxSalary ;
	private double minSalary ;
	private double avgSalary ;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAvgSalary() {
		return avgSalary;
	}
	public void setAvgSalary(double avgSalary) {
		this.avgSalary = avgSalary;
	}
	public double getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}
	public double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}
 
}
