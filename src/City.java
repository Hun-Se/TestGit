import java.util.List;

public class City {
	String cityName;
	int count;
	double maxSalary;  
	double minSalary;  
	double avgSalary;
	String firstHireDate; // 결과값 1987-06-17
	String lastHireDate;  // 결과값 1999-12-07
	
	public City(int count, double  maxSalary, double  minSalary, double  avgSalary, String firstHireDate, String lastHireDate) {
		this.count = count;
		this.maxSalary = maxSalary;
		this.minSalary = minSalary;
		this.avgSalary = avgSalary;
		this.firstHireDate = firstHireDate;
		this.lastHireDate = lastHireDate;
 	}
	public int getCount() {
		return count;
	}
	public double getmaxSalary() {
		return maxSalary;
	}
	public double getminSalary() {
		return minSalary;
	}
	public double getavgSalary() {
		return avgSalary;
	}
	public String getfirstHireDate() {
		return firstHireDate;
	}
	public String getlastHireDate() {
		return lastHireDate;
	}
}
