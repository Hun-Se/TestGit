import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StaticsPrinter {
	
	static void printEmpInfoByManager(){
		Scanner sc = new Scanner(System.in);
		System.out.print("부서장 이름: " );
		String managerName = sc.nextLine();
		
		while(!managerName.equals("끝")) {			
			try {			
				List<EmpByManagerName> list = EmpDAO.getEmpInfoByManagerName(managerName);
					for(Emp emp : list) {
						System.out.println(emp);
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
		System.out.println("종료되었습니다.");
	
	}
}
