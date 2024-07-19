
public class Emp {

	String lastName;
	String hireDate;
	double salary;
	String departmentName;
	
	
	@Override
	public String toString() {
		return "직원정보 [이름: " + lastName + ", 입사일: " + hireDate + ", 연봉: " + salary + ", 부서명: "
				+ departmentName + "]";
	}
	
	
}
