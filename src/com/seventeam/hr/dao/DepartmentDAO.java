package com.seventeam.hr.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.seventeam.hr.domain.Department;
import com.seventeam.hr.util.DBUtil;
import com.teamseven.hr.statistics.DepartmentStatistics;

public class DepartmentDAO {
	
	static void checkDptException(String dptIdStr) throws Exception {
		if (dptIdStr.length() == 0)
			throw new Exception("Enter를 누르셨습니다.");
		Connection con = DBUtil.getConnection();
		String sql = "select count(*) from departments where department_id = ?";
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1, dptIdStr);
		ResultSet rss = psmt.executeQuery();
		rss.next();
		if (rss.getInt("count(*)") == 0)
			throw new Exception("없는 부서 번호 입니다.");
	}
	
	// 부서번호 검색 : 부서정보
	public static List<Department> getDptListByDptId(int dptId) throws Exception {
		String dptIdStr = String.valueOf(dptId);
		
		// 아무것도 입력 안되어있을때, 조회 결과가 없을때 체크해서 에러 throws
		checkDptException(dptIdStr);
		
		List<Department> result = new ArrayList<Department>();
		Connection con = DBUtil.getConnection();
		String sql = "select department_id, department_name, manager_id, location_id from departments where department_id = ? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dptId);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int departmentId = rs.getInt("department_id");
			String departmentName = rs.getString("department_name");
			int managerId = rs.getInt("manager_id");
			int locationId = rs.getInt("location_id");

			Department dptinfo = new Department();

			dptinfo.setDepartmentId(departmentId);
			dptinfo.setDepartmentName(departmentName);
			dptinfo.setManagerId(managerId);
			dptinfo.setLocationId(locationId);

			result.add(dptinfo);
		}
		return result;
	}

	// 부서번호 : 부서 수
	public static DepartmentStatistics getDptCountByDptId(int dptId) throws Exception {
		String dptIdStr = String.valueOf(dptId);
		
		checkDptException(dptIdStr);
		
		List<DepartmentStatistics> result = new ArrayList<DepartmentStatistics>();
		Connection con = DBUtil.getConnection();
		Statement stmt = con.createStatement();
		String sql = "select count(*) from departments";

		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int count = rs.getInt("count(*)");
		DepartmentStatistics dptStatis = new DepartmentStatistics();
		dptStatis.setCount(count);
		return dptStatis;
	}

	// 부서번호 : 부서 최고 연봉자
	public static DepartmentStatistics getDptMaxSalaryByDptId(int dptId) throws Exception {
		String dptIdStr = String.valueOf(dptId);
		checkDptException(dptIdStr);
		
		
		Connection con = DBUtil.getConnection();
		String sql = "select concat(first_name, last_name) name , (e.salary * (1 + ifnull(e.commission_pct,0)) * 12) salary from  employees e join departments d on e.department_id = d.department_id where e.department_id = ? order by e.salary desc limit 1";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dptId);
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		String name = rs.getString("name");
		double salary = rs.getDouble("salary");

		DepartmentStatistics dptStatis = new DepartmentStatistics();
		dptStatis.setName(name);
		dptStatis.setMaxSalary(salary);

		return dptStatis;
	}

	// 부서번호 : 부서 최소 연봉자
	public static DepartmentStatistics getDptMinSalaryByDptId(int dptId) throws Exception {
		String dptIdStr = String.valueOf(dptId);
		checkDptException(dptIdStr);
		
		Connection con = DBUtil.getConnection();
		String sql = "select concat(first_name, last_name) name , (e.salary * (1 + ifnull(e.commission_pct,0)) * 12) salary from  employees e join departments d on e.department_id = d.department_id where e.department_id = ? order by e.salary limit 1";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dptId);
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		String name = rs.getString("name");
		double minSalary = rs.getDouble("salary");

		DepartmentStatistics dptStatis = new DepartmentStatistics();
		dptStatis.setName(name);
		dptStatis.setMinSalary(minSalary);

		return dptStatis;

	}

	// 부서번호 : 부서평균 연봉
	public static DepartmentStatistics getDptAvgSalaryByDptId(int dptId) throws Exception {
		String dptIdStr = String.valueOf(dptId);
		checkDptException(dptIdStr);
		
		
		List<DepartmentStatistics> result = new ArrayList<DepartmentStatistics>();
		Connection con = DBUtil.getConnection();
		String sql = "select avg((e.salary * (1 + ifnull(e.commission_pct,0)) * 12)) salary from employees e join departments  d on e.department_id = d.department_id where d.department_id = ? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dptId);
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		double avgSalary = rs.getDouble("salary");

		DepartmentStatistics dptStatis = new DepartmentStatistics();
		dptStatis.setAvgSalary(avgSalary);

		return dptStatis;
	}

	// 부서번호 : 부서 가장 먼저 입사자
	public static List<DepartmentStatistics> getDptFirstEmpByDptId(int dptId) throws Exception {
		String dptIdStr = String.valueOf(dptId);
		checkDptException(dptIdStr);
		
		List<DepartmentStatistics> result = new ArrayList<DepartmentStatistics>();
		Connection con = DBUtil.getConnection();
		String sql = "select concat(first_name, last_name) name from employees e join departments d on e.department_id = d.department_id where e.department_id = ? order by hire_date limit 1";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dptId);
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		String name = rs.getString("name");

		DepartmentStatistics dptStatis = new DepartmentStatistics();
		dptStatis.setName(name);

		result.add(dptStatis);
		return result;

	}
}
