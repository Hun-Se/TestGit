import java.util.InputMismatchException;
import java.util.Scanner;

public class Start {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("📃『원하는 통계자료를 알려주는 프로그램입니다』");
		String searchNumber = "";
		while(!searchNumber.equals("6")) {
			try {
				System.out.println("*원하시는 검색어 번호를 선택해주세요*");
				System.out.println("1.사용자의 이름 2.입사년도 3.부서번호 4.도시 이름 5.부서장의 부서원 정보 6.끝");
				
				searchNumber = scan.nextLine();
				if (searchNumber.equals("1")) {
					StaticsPrinter.printEmpListByName();
				} else if (searchNumber.equals("2")) {
					StaticsPrinter.printInfoByHireYear();	
				} else if (searchNumber.equals("3")) {
					System.out.println("부서번호를 입력해주세요 :");
					int deptId = scan.nextInt(); 
				} else if (searchNumber.equals("4")) {	
					DtailSelect.cityNameDetail();
				} else if (searchNumber.equals("5")) {
					StaticsPrinter.printEmpInfoByManagerName();
				} else if(searchNumber.equals("6")) {
					// 끝
						break;
				} else {
					System.out.println("잘못된 번호를 입력하셨습니다. 1~5번 중에서 입력해주세요!");
				}
			
			} catch (NumberFormatException e) {
				System.err.println("통계자료 출력 NumberFormatException 에러 발생");
				e.printStackTrace();
				
			} catch (InputMismatchException e) {
				System.err.println("통계자료 출력 InputMismatchException 에러 발생");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("통계자료 출력 Exception 에러 발생");
				e.printStackTrace();
			}
		}
			
		System.out.println("종료하였습니다.");
	
	
	}
}
