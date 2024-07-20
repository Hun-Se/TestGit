//import java.util.Scanner;
//
//public class InputHandler {
//	
//	static void handleMain() {
//		Scanner scan = new Scanner(System.in);
//		System.out.println("[원하는 통계자료를 알려주는 프로그램입니다.]");
//		
//		while(true) {
//			System.out.println("*원하시는 검색어 번호를 선택해주세요* \n1.사용자의 이름 2.입사년도 3.부서번호 4.도시 이름 5.부서장 이름 :");
//			int searchNumber = scan.nextInt();
//			scan.nextLine();
//			if (searchNumber == 1) {
//				System.out.println("사용자의 이름을 입력해주세요 :");
//				String lastName = scan.nextLine(); 
//					
//			}else if (searchNumber == 2) {
//				System.out.println("입사년도를 입력해주세요 :");
//				int hireDate = scan.nextInt(); 
//				System.out.println("원하시는 정보를 선택해주세요 1.");
//				
//				
//			}else if (searchNumber == 3) {
//				System.out.println("부서번호를 입력해주세요 :");
//				int deptId = scan.nextInt(); 
//				System.out.println("원하시는 정보를 선택해주세요 1.");
//				
//				
//			}else if (searchNumber == 4) {
//				System.out.println("도시 이름을 입력해주세요 :");
//				String cityName = scan.nextLine(); 
//				
//				OutSelect outselect = new OutSelect();
//				outselect.cityNamePlus(cityName);
//
//			} else if (searchNumber == 5) {
//				System.out.println("부서장 이름을 입력해주세요 :");
//				String managerName = scan.nextLine(); 
//				
//				
//			} else {
//				System.out.println("잘못된 번호를 입력하셨습니다. 1~5번 중에서 입력해주세요!");
//			
//			
//			}
//		}
//	}
//	
//}
