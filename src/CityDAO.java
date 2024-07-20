import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CityDAO {
	
	public static CityStatistics getCityListByCity(String cityName) throws Exception{
		
		if(cityName.length() == 0 ) throw new Exception("도시 이름이 없습니다. 다시 적어주세요");
		String sql = "select count(*) from locations where city = ?";
		Connection con = DBUtil.getConnection(); 
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1,cityName);
		ResultSet rs = psmt.executeQuery(); 
		rs.next();
		if (rs.getInt("count(*)") == 0) throw new Exception("없는 도시 이름입니다. 다시 입력해주세요.");
		
		sql= "select count(*) ,"
				+ "round(avg(salary*(1+coalesce(commission_pct,0))*12),3) avgSalary, "
				+ "round(max(salary*(1+coalesce(commission_pct,0))*12),3) maxSalary, "
				+ " round(min(salary*(1+coalesce(commission_pct,0))*12),3) minSalary, "
				+ "max(hire_date), min(hire_date) "
				+ "from employees where department_id in "
				+ "(select department_id from departments where location_id in "
				+ "(select location_id from locations where city = ? ))";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,cityName);
		ResultSet rs2 = pstmt.executeQuery(); 
		rs2.next();
		int count = rs2.getInt("count(*)");
		double maxSalary = rs2.getInt("maxSalary");
		double minSalary = rs2.getInt("minSalary");
		double avgSalary = rs2.getInt("avgSalary");
		String firstHireDate = rs2.getString("min(hire_date)"); 
		String lastHireDate = rs2.getString("max(hire_date)");
		CityStatistics city = new CityStatistics(count, maxSalary, minSalary, avgSalary, firstHireDate, lastHireDate);
		
		return city;
	}


}
