import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StaticsPrinter {
	
	static void printEmpInfoByManagerName(){
		Scanner sc = new Scanner(System.in);
		System.out.print("부서장 이름: " );
		String managerName = sc.nextLine();
		
		while(!managerName.equals("끝")) {			
			try {			
				List<Emp> list = EmpDAO.getEmpInfoByManagerName(managerName);
					for(Emp emp : list) {
						System.out.println("사원이름: "+ emp.name + ", 연봉: " + emp.salary + ", 입사일: " + emp.hireDate + ", 부서명: " + emp.departmentName);
					}
					System.out.print("부서장 이름: " );
					managerName = sc.nextLine();	
			} 	
			catch (Exception e) {
				System.out.println(e.getMessage()+ ". 다시 입력하세요.");
				System.out.print("부서장 이름: " );
				managerName = sc.nextLine();
			
			}
			
		}
		System.out.println("처음으로 돌아갑니다.");
	
	}
	
	static void printEmpListByName(){
		Scanner sc = new Scanner(System.in);
		System.out.print("사원이름: " );
		String empName = sc.nextLine();
		
		while(!empName.equals("끝")) {			
			try {			
				List<Emp> list = EmpDAO.getEmpListByName(empName);
					for(Emp emp : list) {
						System.out.println("연봉: " + emp.salary +", 입사일: " + emp.hireDate +", 부서장번호" + emp.managerId +", 부서번호 " + emp.departmentId );
					}
					System.out.print("사원이름: " );
					empName = sc.nextLine();	
			} 	
			catch (Exception e) {
				System.out.println(e.getMessage()+ ". 다시 입력하세요.");
				System.out.print("사원이름: " );
				empName = sc.nextLine();
			}	
		}
		System.out.println("처음으로 돌아갑니다.");
	
	}
	static void printInfoByHireYear(){
		Scanner sc = new Scanner(System.in);
		System.out.print("입사년도: " );
		int hireYear = sc.nextInt();
		
		while(hireYear != 6) {
			
			try {	
					HireYear.getYearStatics(hireYear);
				
					System.out.print("입사년도: " );
					hireYear = sc.nextInt();	
			} 	
			catch (Exception e) {
				System.out.println(e.getMessage()+ ". 다시 입력하세요.");
				System.out.print("입사년도: " );
				hireYear = sc.nextInt();
				HireYear.getYearStatics(hireYear);
				
			
			}
			
		}
		System.out.println("처음으로 돌아갑니다.");
	
	}
	
	static void printEmpInfoByManager(){
		Scanner sc = new Scanner(System.in);
		System.out.print("부서장 이름: " );
		String managerName = sc.nextLine();
		
		while(!managerName.equals("끝")) {			
			try {			
				List<Emp> list = EmpDAO.getEmpInfoByManagerName(managerName);
					for(Emp emp : list) {
						System.out.println("사원이름: "+ emp.name + ", 연봉: " + emp.salary + ", 입사일: " + emp.hireDate + ", 부서명: " + emp.departmentName);
					}
					System.out.print("부서장 이름: " );
					managerName = sc.nextLine();	
			} 	
			catch (Exception e) {
				System.out.println(e.getMessage()+ ". 다시 입력하세요.");
				System.out.print("부서장 이름: " );
				managerName = sc.nextLine();
			
			}
			
		}
		System.out.println("처음으로 돌아갑니다.");
	
	}
	
}
