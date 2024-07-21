package com.seventeam.hr.util;
import java.util.Scanner;

public class DetailSelect {

	public static void dptDetail() throws Exception {

		while (true) {
			System.out.println("부서번호를 입력해주세요");
			Scanner scan = new Scanner(System.in);
			System.out.print("부서번호: ");
			int deptId = scan.nextInt();
			

			System.out.println("원하시는 정보의 번호를 입력해주세요");
			System.out.println(
					"1.부서 정보출력 2. 부서 개수 출력 3. 부서 최대 연봉자 4.부서 최소연봉자 5. 부서 평균연봉 6. 부서 가장 먼저 입사한 직원 7. 처음화면으로 돌아가기");
			int selectNum = scan.nextInt();
			if (selectNum == 1) {
				StaticsPrinter.printInfoByDpt(deptId);
			} else if (selectNum == 2) {
				StaticsPrinter.printCountByDpt(deptId);
			} else if (selectNum == 3) {
				StaticsPrinter.printMaxSalaryByDpt(deptId);
			} else if (selectNum == 4) {
				StaticsPrinter.printMinSalaryByDpt(deptId);
			} else if (selectNum == 5) {
				StaticsPrinter.printAvgSalaryByDpt(deptId);
			} else if (selectNum == 6) {
				StaticsPrinter.printFirstEmpByDpt(deptId);
			} else if (selectNum == 7) {
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다. 1~7까지 입력해주세요.");
			}

		}
	}

	public static void cityNameDetail() throws Exception {

		while (true) {
			Scanner scan = new Scanner(System.in);

			System.out.println("도시 이름(영어)을 입력해주세요");
			System.out.print("도시 이름: ");
			String cityName = scan.nextLine();
	
			
			System.out.println("---------------------------------------------------------------------------");

			System.out.println("원하시는 정보의 번호를 입력해주세요");
			System.out.println(
					"1.도시 개수 2. 도시별 최대연봉 3. 도시별 최소연봉 4.도시별 평균 연봉 5. 도시별 가장 먼저 입사한 사원 6. 도시별 가장 늦게 입사한 사원 7. 처음화면으로 돌아가기");
			
			System.out.print("번호입력: ");

			int selectNumber = scan.nextInt();
			if (selectNumber == 1) {
				System.out.println("도시 개수를 선택하셨습니다.");
				StaticsPrinter.printCityCount(cityName);
			} else if (selectNumber == 2) {
				System.out.println("도시별 최대연봉 *정보를 선택하셨습니다.");
				StaticsPrinter.printCityMaxSalary(cityName);
			} else if (selectNumber == 3) {
				System.out.println("도시별 최소 연봉 *정보를 선택하셨습니다.");
				StaticsPrinter.printCityMinSalary(cityName);
			} else if (selectNumber == 4) {
				System.out.println("도시별 평균 연봉 *정보를 선택하셨습니다.");
				StaticsPrinter.printCityAvgSalary(cityName);
			} else if (selectNumber == 5) {
				System.out.println("도시별 가장 먼저 입사한 사원 *정보를 선택하셨습니다.");
				StaticsPrinter.printCityFirstHireDate(cityName);
			} else if (selectNumber == 6) {
				System.out.println("도시별 가장 늦게 입사한 사원 *정보를 선택하셨습니다.");
				StaticsPrinter.printCityLastHireDate(cityName);
			} else if (selectNumber == 7) {
				System.out.println("처음화면으로 돌아갑니다.");
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다. 1~7번 중에서 입력해주세요!");
			}
		}
	}
}
