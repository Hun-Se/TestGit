import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmpDAO {
	
	public static List<EmpByManagerName> getEmpInfoByManagerName(String managerName) throws Exception {
		
		if(managerName.length() == 0) throw new Exception("Enter를 누르셨습니다");
		Connection con = DBUtil.getConnection();	
		String sql = "select count(*) from employees where last_name = ? or first_name = ?"  ;
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1, managerName);
		psmt.setString(2, managerName);
		ResultSet rss = psmt.executeQuery();
		rss.next();	
		if(rss.getInt("count(*)") == 0) throw new Exception("없는 이름입니다");
		List<EmpByManagerName> result = new ArrayList<>();
		sql = "select e.last_name, d.department_name, (e.salary*(1 + ifnull(e.commission_pct,0))*12) salary, e.hire_date \r\n"
				+ "from employees e join employees m on e.manager_id = m.employee_id join departments d on e.department_id = d.department_id \r\n"
				+ "where e.manager_id in (select employee_id from employees c where last_name = ?" + " or first_name = ? ) "  ;
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, managerName);
		pstmt.setString(2, managerName);
		ResultSet rs = pstmt.executeQuery(); 
		
		
			while(rs.next()){
				int salary = rs.getInt("salary");
				String lastName = rs.getString("last_name");
				Date hireDate = rs.getDate("hire_date");
				String departmentName = rs.getString("department_name");
				EmpByManagerName emp = new EmpByManagerName();
				emp.name = lastName;
				emp.salary = salary;
				emp.hireDate = hireDate;
				emp.departmentName = departmentName;
				
				result.add(emp);
				
			}
		
		return result ;
		
			
		
	}
	
}
