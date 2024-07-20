import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
	//부서번호 검색 : 부서정보
		public static List<Department> getDptListByDptId(int dptId) throws Exception {

			List<Department>result = new ArrayList<Department>();
			Connection con = DBUtil.getConnection();
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
		public static DepartmentStatistics getDptCountByDptId(int dptId) throws Exception {
			List<DepartmentStatistics>result = new ArrayList<DepartmentStatistics>();
			Connection con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(String.valueOf(dptId));
			pstmt.setInt(1,dptId);
			Statement stmt = con.createStatement();
			String sql1 = "select count(*) from departments";
			
			ResultSet rs1 = stmt.executeQuery(sql1);
			rs1.next();
			int count = rs1.getInt("count(*)");
			DepartmentStatistics dptStatis = new DepartmentStatistics();
			dptStatis.count = count;
			return dptStatis;
		}

		//부서번호 : 부서 최고 연봉자
		public static DepartmentStatistics getDptMaxSalaryByDptId(int dptId) throws Exception {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(String.valueOf(dptId));
			pstmt.setInt(1,dptId);
			Statement stmt = con.createStatement();
			String sql1 = "select concat(first_name, last_name) name , (e.salary * (1 + ifnull(e.commission_pct,0)) * 12) salary from  employees e join departments d on e.department_id = d.department_id where e.department_id = ? order by e.salary desc limit 1";
			
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			rs1.next();
			String name = rs1.getString("name");
			double salary = rs1.getDouble("salary");
				
				
			DepartmentStatistics dptStatis = new DepartmentStatistics();
			dptStatis.name = name;
			dptStatis.maxSalary = salary;
			
			return dptStatis;
		}
		
		//부서번호 : 부서 최소 연봉자
		public static DepartmentStatistics getDptMinSalaryByDptId(int dptId) throws Exception {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(String.valueOf(dptId));
			pstmt.setInt(1,dptId);
			Statement stmt = con.createStatement();
			String sql1 = "select concat(first_name, last_name) name , (e.salary * (1 + ifnull(e.commission_pct,0)) * 12) salary from  employees e join departments d on e.department_id = d.department_id where e.department_id = ? order by e.salary limit 1";
			
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			rs1.next();
			String name = rs1.getString("name");
			double minSalary = rs1.getDouble("salary");
				
				
			DepartmentStatistics dptStatis = new DepartmentStatistics();
			dpt.name = name;
			dpt.minSalary = minSalary;
			
			return dptStatis;
			
		}
		
		//부서번호 : 부서평균 연봉
		public static DepartmentStatistics getDptAvgSalaryByDptId(int dptId) throws Exception {
			List<DepartmentStatistics>result = new ArrayList<DepartmentStatistics>();
			Connection con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(String.valueOf(dptId));
			pstmt.setInt(1,dptId);
			Statement stmt = con.createStatement();
			String sql1 = "select avg((e.salary * (1 + ifnull(e.commission_pct,0)) * 12)) salary from employees e join departments  d on e.department_id = d.department_id where d.department_id = ?; ";
			
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			rs1.next();
			double avgSalary = rs1.getDouble("salary");
				
				
			DepartmentStatistics dptStatis = new DepartmentStatistics();
			dptStatis.avgSalary = avgSalary;
	
			return dptStatis;			
	}
		
		//부서번호 : 부서 가장 먼저 입사자
		public static DepartmentStatistics getDptFirstEmpByDptId(int dptId) throws Exception {
			List<DepartmentStatistics>result = new ArrayList<DepartmentStatistics>();
			Connection con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(String.valueOf(dptId));
			pstmt.setInt(1,dptId);
			Statement stmt = con.createStatement();
			String sql1 = "select concat(first_name, last_name) name from employees e join departments d on e.department_id = d.department_id where e.department_id = ? order by hire_date limit 1";
			
			
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			rs1.next();
			String name = rs1.getString("name");
			
			DepartmentStatistics dpt = new DepartmentStatistics();
			dpt.name = name;
			
				result.add(dpt);
			return result;
			
		}
}
