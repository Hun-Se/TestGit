import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class printTest {

	//이름 검색 기능
	public static List<Emp> getEmpListByName (String name) throws Exception{
		List<Emp> result = new ArrayList<Emp>();
		Connection con = DBUtill.getConnection();
		PreparedStatement pstmt = con.prepareStatement(name);
		pstmt.setString(1, name); //들어온 파라미터를 넣는다.
		pstmt.setString(2, name);
		Statement stmt = con.createStatement();
		String sql = "select hire_date, salary, manager_id, department_id, city, department_name from employees e "
				+ "join departments d on e.department_id = d.department_id "
				+ "join locations l on  l.location_id = d.location_id  "
				+ "where first_name = ? or last_name = ? ";

		ResultSet rs = stmt.executeQuery(sql); // stmt인터페이스에 선언된 메서드이자 리턴타입은 resultset
		
		while (rs.next()) { //가져올 데이터
			Date hireDate = rs.getDate("hire_date"); 
			Double salary = rs.getDouble("salary"); 
			int managerId = rs.getInt("manager_id");
			int departmentId = rs.getInt("department_id");
			String cityName = rs.getString("city");
			String departmentName = rs.getString("department_name");
			
			//도시이름 = 부서아이디랑 조인? 하는 방법 생각해보기 > 다른거 틀 다 만든 다음에
			
			Emp emp = new Emp();
			emp.hireDate = hireDate;
			emp.salary = salary;
			emp.managerId = managerId;
			emp.departmentId = departmentId;
			
				result.add(emp);
			}
			return result;
		
	}
	
	
	
	//입사년도 검색 기능 : 입사인원 수, 평균 연봉
	public static List<EmpStatistics> getInfoByHireYear(int year) throws Exception {
		List<EmpStatistics> result = new ArrayList<EmpStatistics>();
		Connection con = DBUtill.getConnection();
		PreparedStatement pstmt = con.prepareStatement(String.valueOf(year));
		pstmt.setInt(1,year);
		Statement stmt = con.createStatement();
		String sql1 = "select count(*), avg((salary * (1 + ifnull(commission_pct,0)) * 12)) salary from employees where Year(hire_date) = ?"; 

		ResultSet rs1 = stmt.executeQuery(sql1);
		
		while (rs1.next()) {
			int count = rs1.getInt("count(*)"); 
			int avgSalary = rs1.getInt("salary");
			
			
			EmpStatistics empInfo = new EmpStatistics();
			empInfo.count = count;
			empInfo.avgSalary = avgSalary;
			
					result.add(empInfo);
			}
			return result;
			
	}
	
	//입사년도 검색 기능 : 그 해 최대 연봉자 이름, 연봉
	public static List<HireDate> getMaxSalaryByYear(int year) throws Exception {
		List<HireDate> result = new ArrayList<HireDate>();
		Connection con = DBUtill.getConnection();
		PreparedStatement pstmt = con.prepareStatement(String.valueOf(year));
		pstmt.setInt(1,year);
		Statement stmt = con.createStatement();
		String sql1 = "select hire_date, concat(first_name, last_name) name , (salary * (1 + ifnull(commission_pct,0)) * 12) salary from employees where Year(hire_date) = ? order by salary desc limit 1";
		
		ResultSet rs1 = stmt.executeQuery(sql1);
		
		while (rs1.next()) {
			Date hireDate = rs1.getDate("hire_date");
			String name = rs1.getString("name");
			double salary = rs1.getDouble("salary");
			
			HireDate maxSal = new HireDate();
			maxSal.hireDate = hireDate;
			maxSal.name = name;
			maxSal.maxSalary = salary;
			
						result.add(maxSal);
		}
		return result;
	}
	
	//입사년도 검색 기능 : 그 해 최소 연봉자 이름, 연봉
	public static List<HireDate> getMinSalaryByYear(int year) throws Exception {
		List<HireDate> result = new ArrayList<HireDate>();
		Connection con = DBUtill.getConnection();
		PreparedStatement pstmt = con.prepareStatement(String.valueOf(year));
		pstmt.setInt(1,year);
		Statement stmt = con.createStatement();
		String sql1 = "select hire_date, concat(first_name, last_name) name , (salary * (1 + ifnull(commission_pct,0)) * 12) salary from employees where Year(hire_date) = ? order by salary limit 1";
		
		ResultSet rs1 = stmt.executeQuery(sql1);
		
		while (rs1.next()) {
			Date hireDate = rs1.getDate("hire_date");
			String name = rs1.getString("name");
			double salary = rs1.getDouble("salary");
			
			HireDate minSal = new HireDate();
			minSal.hireDate = hireDate;
			minSal.name = name;
			minSal.minSalary = salary;
			
					result.add(minSal);
					
		}
		return result;
	}
	
	// 입사년도 검색 : 그 해 가장 먼저 입사한 사람 
	public static List<Emp> getEmpByYear(int year) throws Exception {
		List<Emp> result = new ArrayList<Emp>();
		Connection con = DBUtill.getConnection();
		PreparedStatement pstmt = con.prepareStatement(String.valueOf(year));
		pstmt.setInt(1,year);
		Statement stmt = con.createStatement();
		String sql1 = "select employee_id, concat(first_name, last_name) name, hire_date, job_id, salary from employees where Year(hire_date) = ? order by hire_date limit 1";
		
		ResultSet rs1 = stmt.executeQuery(sql1);
		
		while (rs1.next()) {
			int employeeId = rs1.getInt("employee_id");
			String name = rs1.getString("name");
			Date hireDate = rs1.getDate("hire_date");
			String jobId = rs1.getString("job_id");
			double salary = rs1.getDouble("salary");
			
			Emp firstInfo = new Emp();
			firstInfo.employeeId = employeeId;
			firstInfo.name = name;
			firstInfo.hireDate = hireDate;
			firstInfo.jobId = jobId;
			firstInfo.salary = salary;
			
					result.add(firstInfo);
			
		}
		return result;
	}
	
	
	
	
	//부서번호 검색 : 부서정보
	public static List<Department> getDptListByDptId(int dptId) throws Exception {

		List<Department>result = new ArrayList<Department>();
		Connection con = DBUtill.getConnection();
		PreparedStatement pstmt = con.prepareStatement(String.valueOf(dptId));
		pstmt.setInt(1,dptId);
		Statement stmt = con.createStatement();
		String sql1 = "select department_id, department_name, manager_id, location_id from departments where department_id = ?";
		
		ResultSet rs1 = stmt.executeQuery(sql1);
		
		while (rs1.next()) {
			int departmentId = rs1.getInt("department_id");
			String departmentName = rs1.getString("department_name");
			int managerId = rs1.getInt("manager_id");
			int locationId = rs1.getInt("location_id");
			
			
			Department dptinfo = new Department();
			
			dptinfo.departmentId = departmentId;
			dptinfo.departmentName = departmentName;
			dptinfo.managerId  = managerId;
			dptinfo.locationId = locationId;
			
					result.add(dptinfo);
			
		}
		return result;
	}
	
	//부서번호 : 부서 수
	public static List<DepartmentStatistics> getDptCountByDptId(int dptId) throws Exception {
		List<DepartmentStatistics>result = new ArrayList<DepartmentStatistics>();
		Connection con = DBUtill.getConnection();
		PreparedStatement pstmt = con.prepareStatement(String.valueOf(dptId));
		pstmt.setInt(1,dptId);
		Statement stmt = con.createStatement();
		String sql1 = "select count(*) from departments";
		
		ResultSet rs1 = stmt.executeQuery(sql1);
		
		while (rs1.next()) {
			int count = rs1.getInt("count(*)");
			
			DepartmentStatistics dptCount = new DepartmentStatistics();
			dptCount.count = count;
		
					result.add(dptCount);
		}	
		return result;
		
	}

	//부서번호 : 부서 최고 연봉자
	public static List<DepartmentStatistics> getDptMaxSalaryByDptId(int dptId) throws Exception {
		List<DepartmentStatistics>result = new ArrayList<DepartmentStatistics>();
		Connection con = DBUtill.getConnection();
		PreparedStatement pstmt = con.prepareStatement(String.valueOf(dptId));
		pstmt.setInt(1,dptId);
		Statement stmt = con.createStatement();
		String sql1 = "select concat(first_name, last_name) name , (e.salary * (1 + ifnull(e.commission_pct,0)) * 12) salary from  employees e join departments d on e.department_id = d.department_id where e.department_id = ? order by e.salary desc limit 1";
		
		ResultSet rs1 = stmt.executeQuery(sql1);
		
		while (rs1.next()) {
			String name = rs1.getString("name");
			double salary = rs1.getDouble("salary");
			
			
			DepartmentStatistics dpt = new DepartmentStatistics();
			dpt.name = name;
			dpt.maxSalary = salary;
		
					result.add(dpt);
		}	
		return result;
		
	}
	
	//부서번호 : 부서 최소 연봉자
	public static List<DepartmentStatistics> getDptMinSalaryByDptId(int dptId) throws Exception {
		List<DepartmentStatistics>result = new ArrayList<DepartmentStatistics>();
		Connection con = DBUtill.getConnection();
		PreparedStatement pstmt = con.prepareStatement(String.valueOf(dptId));
		pstmt.setInt(1,dptId);
		Statement stmt = con.createStatement();
		String sql1 = "select concat(first_name, last_name) name , (e.salary * (1 + ifnull(e.commission_pct,0)) * 12) salary from  employees e join departments d on e.department_id = d.department_id where e.department_id = ? order by e.salary limit 1";
		
		ResultSet rs1 = stmt.executeQuery(sql1);
		
		while (rs1.next()) {
			String name = rs1.getString("name");
			double minSalary = rs1.getDouble("salary");
			
			
			DepartmentStatistics dpt = new DepartmentStatistics();
			dpt.name = name;
			dpt.minSalary = minSalary;
		
					result.add(dpt);
		}	
		return result;
		
	}
	
	//부서번호 : 부서평균 연봉
	public static List<DepartmentStatistics> getDptAvgSalaryByDptId(int dptId) throws Exception {
		List<DepartmentStatistics>result = new ArrayList<DepartmentStatistics>();
		Connection con = DBUtill.getConnection();
		PreparedStatement pstmt = con.prepareStatement(String.valueOf(dptId));
		pstmt.setInt(1,dptId);
		Statement stmt = con.createStatement();
		String sql1 = "select avg((e.salary * (1 + ifnull(e.commission_pct,0)) * 12)) salary from employees e join departments  d on e.department_id = d.department_id where d.department_id = ?; ";
		
		ResultSet rs1 = stmt.executeQuery(sql1);
		
		while (rs1.next()) {
			double avgSalary = rs1.getDouble("salary");
			
			
			DepartmentStatistics dpt = new DepartmentStatistics();
			dpt.avgSalary = avgSalary;
		
					result.add(dpt);
		}	
		return result;
		
}
	
	//부서번호 : 부서 가장 먼저 입사자
	public static List<DepartmentStatistics> getDptFirstEmpByDptId(int dptId) throws Exception {
		List<DepartmentStatistics>result = new ArrayList<DepartmentStatistics>();
		Connection con = DBUtill.getConnection();
		PreparedStatement pstmt = con.prepareStatement(String.valueOf(dptId));
		pstmt.setInt(1,dptId);
		Statement stmt = con.createStatement();
		String sql1 = "select concat(first_name, last_name) name from employees e join departments d on e.department_id = d.department_id where e.department_id = ? order by hire_date limit 1";
		
		
		ResultSet rs1 = stmt.executeQuery(sql1);
		
		while (rs1.next()) {
			String firstName = rs1.getString("name");
			
			
			DepartmentStatistics dpt = new DepartmentStatistics();
			dpt.firstName = firstName;
		
					result.add(dpt);
		}	
		return result;
		
	}
	
}

		
	
		



	


