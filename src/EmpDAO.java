import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	
	public static List<Emp> getEmpInfoByManagerName(String managerName) throws Exception {
		
		Connection con = DBUtil.getConnection();
		if(managerName.length() == 0) throw new Exception("길이가 짧아요");	
		List<Emp> result = new ArrayList<>();
		String sql = "select c.last_name, c.hire_date, d.department_name, (c.salary*(1 + ifnull(c.commission_pct,0))*12) salary from employees c join departments d on c.department_id = d.department_id where employee_id in (select e.employee_id from employees e join employees m on e.manager_id = m.employee_id \r\n"
				+ "where e.manager_id in (select employee_id from employees where last_name = ? ))"  ;
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, managerName);
		ResultSet rs = pstmt.executeQuery(); 

		while(rs.next()){
			int salary = rs.getInt("salary");
			String lastName = rs.getString("last_name");
			String hireDate = rs.getString("hire_date");
			String departmentName = rs.getString("department_name");
			Emp emp = new Emp();
			emp.lastName = lastName;
			emp.salary = salary;
			emp.hireDate = hireDate;
			emp.departmentName = departmentName;
			
			result.add(emp);	
		}
			return result ;
		
	}
	
}
