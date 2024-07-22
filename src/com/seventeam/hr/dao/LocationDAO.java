package com.seventeam.hr.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.seventeam.hr.util.DBUtil;
import com.teamseven.hr.statistics.LocationStatistics;

public class LocationDAO {
	
	public static int getCityCoutByCity() throws Exception {
		
		Connection con = DBUtil.getConnection();
		Statement stmt = con.createStatement();
		String sql = "select count(*) from locations where city is not null ";
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int count = rs.getInt("count(*)");
		LocationStatistics city = new LocationStatistics();
		city.setCount(count);
		
		return count;
	}
	
	public static LocationStatistics getCityListByCity(String cityName) throws Exception{
		if (cityName.length() == 0) throw new Exception("Enter를 누르셨습니다.");
		String sql= "select count(*) ,"
				+ "round(avg(salary*(1+coalesce(commission_pct,0))*12),3) avgSalary, "
				+ "round(max(salary*(1+coalesce(commission_pct,0))*12),3) maxSalary, "
				+ " round(min(salary*(1+coalesce(commission_pct,0))*12),3) minSalary, "
				+ "max(hire_date), min(hire_date) "
				+ "from employees where department_id in "
				+ "(select department_id from departments where location_id in "
				+ "(select location_id from locations where city = ? ))";
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, cityName);
		ResultSet rs2 = pstmt.executeQuery(); 
		rs2.next();
		if (rs2.getInt("count(*)")== 0) throw new Exception("없는 도시 이름 입니다. 다시 입력해 주세요.");
		int count = rs2.getInt("count(*)");
		double maxSalary = rs2.getInt("maxSalary");
		double minSalary = rs2.getInt("minSalary");
		double avgSalary = rs2.getInt("avgSalary");
		String firstHireDate = rs2.getString("min(hire_date)"); 
		String lastHireDate = rs2.getString("max(hire_date)");
		LocationStatistics city = new LocationStatistics(count, maxSalary, minSalary, avgSalary, firstHireDate, lastHireDate);
		
		return city;
	}


}
