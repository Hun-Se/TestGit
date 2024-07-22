import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.seventeam.hr.util.DetailSelect;
import com.seventeam.hr.util.StaticsPrinter;

public class Start {

	public static void main(String[] args) {
		StaticsPrinter.printTeam();
		System.out.println("📃『원하는 통계자료를 알려주는 프로그램입니다』");
		while (true) {
				System.out.println("*원하시는 검색어 번호를 선택해주세요*");
				System.out.println("1.사용자의 이름 2.입사년도 3.부서번호 4.도시 이름 5.부서장의 부서원 정보 6.끝");
				Scanner scan = new Scanner(System.in);
				System.out.print("입력: ");
				int searchNumber = scan.nextInt();
				
				if (searchNumber == 1) {
					StaticsPrinter.printEmpListByName();
				} else if (searchNumber == 2) {
					StaticsPrinter.printInfoByHireYear();
				} else if (searchNumber == 3) {
					DetailSelect.dptDetail();
				} else if (searchNumber == 4) {
					DetailSelect.cityNameDetail();
				} else if (searchNumber == 5) {
					StaticsPrinter.printEmpInfoByManagerName();
				} else if (searchNumber == 6) {
					// 끝
					break;
				} else {
					System.out.println("---------------------------------------------------------------------------");
					System.out.println("잘못된 번호를 입력하셨습니다. 1~6번 중에서 입력해주세요!");
					System.out.println("---------------------------------------------------------------------------");
				}

			} 
		System.out.println("종료하였습니다.");
		}
}
