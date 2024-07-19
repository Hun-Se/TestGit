import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
	
	public static List<City> getCityListByCity(String cityName) throws Exception{
		List<City> result = new ArrayList<City>();
		
		Connection con = DBUtil.getConnection(); 
		Statement stmt = con.createStatement();   
		String sql = "select count(*),avg(salary), max(salary), min(salary), max(hire_date), min(hire_date)\r\n"
				+ "from employees\r\n"
				+ "where department_id in (select department_id from departments where location_id in \n"
				+ "(select location_id from locations where city = ? ))";
//		String sql2 = "select avg(salary), max(salary), min(salary) from employees where department_id  in \n"
//				+ "(select department_id from departments where location_id in \n"
//				+ "(select location_id from locations where city = " + cityName + "))";
//		String sql3 = "select max(hire_date), min(hire_date) from employees where department_id  in \n"
//				+ "(select department_id from departments where location_id in \n"
//				+ "(select location_id from locations where city = " + cityName + "))";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,cityName);
		ResultSet rs = pstmt.executeQuery(sql); 
//		ResultSet rs2 = stmt.executeQuery(sql2);
//		ResultSet rs3 = stmt.executeQuery(sql3);
		while(rs.next()) {
			int count = rs.getInt("count(*)");
			double maxSalary = rs.getInt("max(salary)"); //***
			double minSalary = rs.getInt("min(salary)");
			double avgSalary = rs.getInt("avg(salary)");
			String firstHireDate = rs.getString("min(hire_date)"); 
			String lastHireDate = rs.getString("max(hire_date)");
			
			City city = new City(count, maxSalary, minSalary, avgSalary, firstHireDate, lastHireDate);
			result.add(city);				
					
		}		
		return result;		
		
	}	
}
