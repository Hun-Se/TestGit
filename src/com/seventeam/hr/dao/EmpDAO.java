package com.seventeam.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.seventeam.hr.domain.Department;
import com.seventeam.hr.domain.Emp;
import com.seventeam.hr.statistics.DepartmentStatistics;
import com.seventeam.hr.statistics.EmpStatistics;
import com.seventeam.hr.util.DBUtil;

public class EmpDAO {

	public static List<Emp> getEmpInfoByManagerName(String managerName) throws Exception {

		if (managerName.length() == 0)
			throw new Exception("Enter를 누르셨습니다");
		Connection con = DBUtil.getConnection();
		String sql = "select count(*) from employees where last_name = ? or first_name = ?";
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1, managerName);
		psmt.setString(2, managerName);
		ResultSet rss = psmt.executeQuery();
		rss.next();
		if (rss.getInt("count(*)") == 0)
			throw new Exception("없는 부서장 이름 입니다");
		List<Emp> result = new ArrayList<Emp>();
		sql = "select e.last_name, d.department_name, (e.salary*(1 + ifnull(e.commission_pct,0))*12) salary, e.hire_date \r\n"
				+ "from employees e join employees m on e.manager_id = m.employee_id join departments d on e.department_id = d.department_id \r\n"
				+ "where e.manager_id in (select employee_id from employees c where last_name = ?"
				+ " or first_name = ? ) ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, managerName);
		pstmt.setString(2, managerName);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			double salary = rs.getDouble("salary");
			String lastName = rs.getString("last_name");
			Date hireDate = rs.getDate("hire_date");
			String departmentName = rs.getString("department_name");
			Emp emp = new Emp();
			emp.setName(lastName);
			emp.setSalary(salary);
			emp.setHireDate(hireDate);
			emp.setDepartmentName(departmentName);

			result.add(emp);

		}

		return result;

	}

	// 이름 검색 기능 /* emp에 이름도 넣어야 할듯 중복 때문에 */
	public static List<Emp> getEmpListByName(String name) throws Exception {
		if (name.length() == 0)
			throw new Exception("Enter를 누르셨습니다");
		String sql = "select count(*) from employees where last_name = ? or first_name = ?";
		List<Emp> result = new ArrayList<Emp>();
		Connection con = DBUtil.getConnection();
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1, name);
		psmt.setString(2, name);
		ResultSet rss = psmt.executeQuery();
		rss.next();
		if (rss.getInt("count(*)") == 0)
			throw new Exception("없는 이름입니다");

		sql = "select e.hire_date, e.salary, e.manager_id, d.department_id, l.city, d.department_name from employees e "
				+ "join departments d on e.department_id = d.department_id "
				+ "join locations l on  l.location_id = d.location_id  " + "where first_name = ? or last_name = ? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name); // 들어온 파라미터를 넣는다.
		pstmt.setString(2, name);
		ResultSet rs = pstmt.executeQuery(); // stmt인터페이스에 선언된 메서드이자 리턴타입은 resultset

		while (rs.next()) { // 가져올 데이터
			Date hireDate = rs.getDate("hire_date");
			Double salary = rs.getDouble("salary");
			int managerId = rs.getInt("manager_id");
			int departmentId = rs.getInt("department_id");
			String cityName = rs.getString("city");
			String departmentName = rs.getString("department_name");

			// 도시이름 = 부서아이디랑 조인? 하는 방법 생각해보기 > 다른거 틀 다 만든 다음에

			Emp emp = new Emp();
			emp.setHireDate(hireDate);
			emp.setSalary(salary);
			emp.setManagerId(managerId);
			emp.setDepartmentId(departmentId);

			result.add(emp);
		}
		return result;

	}

	// 입사년도 검색 기능 : 입사인원 수, 평균 연봉
	public static List<EmpStatistics> getInfoByHireYear(int year) throws Exception {

		String yearStr = String.valueOf(year);
		if (yearStr.length() == 0)
			throw new Exception("Enter를 누르셨습니다");
		List<EmpStatistics> result = new ArrayList<EmpStatistics>();
		Connection con = DBUtil.getConnection();
		String sql = "select count(*) from employees where year(hire_date) = ?";
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setInt(1, year);
		ResultSet rss = psmt.executeQuery();
		rss.next();
		if (rss.getInt("count(*)") == 0)
			throw new Exception("해당 입사년도의 데이터가 없습니다.");
		sql = "select count(*) count, avg((salary * (1 + ifnull(commission_pct,0)) * 12)) salary from employees where Year(hire_date) = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, year);
		ResultSet rs = pstmt.executeQuery();
		rs.next();

		EmpStatistics emp = new EmpStatistics();
		emp.setCount(rs.getInt("count"));
		emp.setAvgSalary(rs.getDouble("salary"));
		result.add(emp);

		return result;

	}

	// 입사년도 검색 기능 : 그 해 최대 연봉자 이름, 연봉
	public static List<EmpStatistics> getMaxSalaryByYear(int year) throws Exception {
		List<EmpStatistics> result = new ArrayList<EmpStatistics>();
		Connection con = DBUtil.getConnection();
		String sql = "select hire_date, concat(first_name, last_name) name , (salary * (1 + ifnull(commission_pct,0)) * 12) salary from employees where Year(hire_date) = ? order by salary desc limit 1";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, year);
		ResultSet rs = pstmt.executeQuery();
		rs.next();

		Date hireDate = rs.getDate("hire_date");
		String name = rs.getString("name");
		double salary = rs.getDouble("salary");

		EmpStatistics emp = new EmpStatistics();
		emp.setHireDate(hireDate);
		emp.setName(name);
		emp.setMaxSalary(salary);

		result.add(emp);

		return result;
	}

	// 입사년도 검색 기능 : 그 해 최소 연봉자 이름, 연봉
	public static List<EmpStatistics> getMinSalaryByYear(int year) throws Exception {
		List<EmpStatistics> result = new ArrayList<EmpStatistics>();
		Connection con = DBUtil.getConnection();
		String sql1 = "select hire_date, concat(first_name, last_name) name , (salary * (1 + ifnull(commission_pct,0)) * 12) salary from employees where Year(hire_date) = ? order by salary limit 1";
		PreparedStatement pstmt = con.prepareStatement(sql1);
		pstmt.setInt(1, year);
		ResultSet rs1 = pstmt.executeQuery();
		rs1.next();

		Date hireDate = rs1.getDate("hire_date");
		String name = rs1.getString("name");
		double salary = rs1.getDouble("salary");

		EmpStatistics minSal = new EmpStatistics();
		minSal.setHireDate(hireDate);
		minSal.setName(name);
		minSal.setMinSalary(salary);

		result.add(minSal);

		return result;
	}

	// 입사년도 검색 : 그 해 가장 먼저 입사한 사람
	public static List<EmpStatistics> getEmpByYear(int year) throws Exception {
		List<EmpStatistics> result = new ArrayList<EmpStatistics>();
		Connection con = DBUtil.getConnection();
		String sql1 = "select employee_id, concat(first_name, last_name) name, hire_date, job_id, salary from employees where Year(hire_date) = ? order by hire_date limit 1";
		PreparedStatement pstmt = con.prepareStatement(sql1);
		pstmt.setInt(1, year);

		ResultSet rs1 = pstmt.executeQuery();
		rs1.next();

		int employeeId = rs1.getInt("employee_id");
		String name = rs1.getString("name");
		Date hireDate = rs1.getDate("hire_date");
		String jobId = rs1.getString("job_id");
		double salary = rs1.getDouble("salary");

		EmpStatistics firstInfo = new EmpStatistics();
		firstInfo.setEmployeeId(employeeId);
		firstInfo.setName(name);
		firstInfo.setHireDate(hireDate);
		firstInfo.setJobId(jobId);
		firstInfo.setSalary(salary);

		result.add(firstInfo);

		return result;
	}



}
