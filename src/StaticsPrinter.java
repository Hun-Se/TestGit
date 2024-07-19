import java.util.List;
import java.util.Scanner;

public class StaticsPrinter {
	
	static void printEmpInfoByManager(){
		Scanner sc = new Scanner(System.in);
		System.out.print("부서장 이름: " );
		String managerName = sc.nextLine();
		
		try {
			while(!managerName.equals("끝")) {
				List<Emp> list = EmpDAO.getEmpInfoByManagerName(managerName);
				for(Emp emp : list) {
					System.out.println(emp);
				}
				System.out.print("부서장 이름: " );
				managerName = sc.nextLine();
			}
		} catch (Exception e) {
			System.out.println("예외가 발생되었습니다. 문자열을 입력하지않고 Enter키를 누르셨습니다.");
			System.out.print("부서장 이름: " );
			managerName = sc.nextLine();
		} finally {
			System.out.println("종료되었습니다.");
		}
	}
	
}
