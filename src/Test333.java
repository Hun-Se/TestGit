import java.util.Scanner;

public class Test333 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		
		while(true) {
			System.out.println("*원하시는 검색어 번호를 선택해주세요* \n1.사용자의 이름 2.입사년도 3.부서번호 4.도시 이름 5.부서장 이름 :");
			int searchNumber = scan.nextInt();
			scan.nextLine();
			if (searchNumber == 4) {
				System.out.println("도시 이름을 입력해주세요 :");
				String cityName = scan.nextLine();
				OutSelect outselect = new OutSelect();
				outselect.cityNamePlus(cityName);
			}
			break;
			
		}
		
		

	}

}
