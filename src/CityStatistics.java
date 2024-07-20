
public class CityStatistics extends City {

	int count;
	double maxSalary;  
	double minSalary;  
	double avgSalary;
	String firstHireDate; // 결과값 1987-06-17
	String lastHireDate;  // 결과값 1999-12-07
	
	public CityStatistics (int count, double  maxSalary, double  minSalary, double  avgSalary, String firstHireDate, String lastHireDate) {
		this.count = count;
		this.maxSalary = maxSalary;
		this.minSalary = minSalary;
		this.avgSalary = avgSalary;
		this.firstHireDate = firstHireDate;
		this.lastHireDate = lastHireDate;
 	}
}
