import java.util.Scanner;

public class OutSelect {
	
	public void cityNamePlus(String cityName) throws Exception {
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("도시 이름: " + cityName);
			System.out.println("원하시는 정보의 번호를 입력해주세요 \n"
					+ "1.도시 개수 2. 도시별 최대연봉 3. 도시별 최소연봉 4.도시별 평균 연봉 5. 도시별 가장 먼저 입사한 사원 6. 도시별 가장 늦게 입사한 사원 7. 처음화면으로 돌아가기");
			int selectNumber = scan.nextInt();
			if(selectNumber == 1) {
				System.out.println("도시 개수를 선택하셨습니다.");
				// 출력함수
				StaticsPrinter.printCityCount(cityName);
			}else if (selectNumber == 2) {
				System.out.println("도시별 최대연봉 *정보를 선택하셨습니다.");
				StaticsPrinter.printCityMaxSalary(cityName);
			}else if (selectNumber == 3) {
				System.out.println("도시별 최소 연봉 *정보를 선택하셨습니다.");
				StaticsPrinter.printCityMinSalary(cityName);
			}else if (selectNumber == 4) {
				System.out.println("도시별 평균 연봉 *정보를 선택하셨습니다.");
				StaticsPrinter.printCityAvgSalary(cityName);
			}else if (selectNumber == 5) {
				System.out.println("도시별 가장 먼저 입사한 사원 *정보를 선택하셨습니다.");
				StaticsPrinter.printCityFirstHireDate(cityName);
			}else if (selectNumber == 6) {
				System.out.println("도시별 가장 늦게 입사한 사원 *정보를 선택하셨습니다.");
				StaticsPrinter.printCityLastHireDate(cityName);
			}else if (selectNumber == 7) {
				System.out.println("처음화면으로 돌아갑니다.");
				break;
			}
				else {
				System.out.println("잘못된 번호를 입력하셨습니다. 1~7번 중에서 입력해주세요!");
			}
		}
	}
}
