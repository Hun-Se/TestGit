package com.teamseven.hr.statistics;

import com.seventeam.hr.domain.City;

public class CityStatistics extends City {

	private int count;
	private double maxSalary;  
	private double minSalary;  
	private double avgSalary;
	private String firstHireDate; // 결과값 1987-06-17
	private String lastHireDate;  // 결과값 1999-12-07
	
	public CityStatistics (int count, double  maxSalary, double  minSalary, double  avgSalary, String firstHireDate, String lastHireDate) {
		this.setCount(count);
		this.setMaxSalary(maxSalary);
		this.setMinSalary(minSalary);
		this.setAvgSalary(avgSalary);
		this.setFirstHireDate(firstHireDate);
		this.setLastHireDate(lastHireDate);
 	}

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

	public double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}

	public double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public String getFirstHireDate() {
		return firstHireDate;
	}

	public void setFirstHireDate(String firstHireDate) {
		this.firstHireDate = firstHireDate;
	}

	public String getLastHireDate() {
		return lastHireDate;
	}

	public void setLastHireDate(String lastHireDate) {
		this.lastHireDate = lastHireDate;
	}
}
