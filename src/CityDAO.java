import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
	
	public static List<City> getCityListByCity(String cityName) throws Exception{
		
		if(cityName.length() == 0 ) throw new Exception("도시 이름이 없습니다. 다시 적어주세요");
		Connection con = DBUtil.getConnection(); 
		Statement stmt = con.createStatement(); 
		String sql = "select count(*) from employees where city = ?";
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1,cityName);
		ResultSet rss = psmt.executeQuery(sql); 
		rss.next();
		if (rss.getInt("count(*)") == 0) throw new Exception("없는 도시 이름입니다. 다시 입력해주세요.");
			List<City> result = new ArrayList<City>();
			sql= "select count(*) ,"
					+ "round(avg(salary*(1+coalesce(commission_pct,0))*12),3) avgSalary,"
					+ "round(max(salary*(1+coalesce(commission_pct,0))*12),3) maxSalary,"
					+ " round(min(salary*(1+coalesce(commission_pct,0))*12),3) minSalary,"
					+ "max(hire_date), min(hire_date)"
					+ "from employeeswhere department_id in "
					+ "(select department_id from departments where location_id in "
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
