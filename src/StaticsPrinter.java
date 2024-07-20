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

		System.out.println("종료되었습니다.");
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
	
	static void printCityCount(String cityName) throws Exception {
		CityStatistics city = CityDAO.getCityListByCity(cityName);
		System.out.println(city.count);
		
	}
	
	static void printCityMaxSalary(String cityName) throws Exception {
		CityStatistics city = CityDAO.getCityListByCity(cityName);
		
		System.out.println("최대연봉");
		System.out.println("도시이름: " + cityName + "최대연봉: " + city.avgSalary );
		System.out.println(city.maxSalary);
	}
	
	static void printCityMinSalary(String cityName) throws Exception {
		CityStatistics city = CityDAO.getCityListByCity(cityName);
		System.out.println("최저연봉");
		System.out.println("도시이름: " + cityName + "최저연봉: " + city.avgSalary );
		System.out.println(city.minSalary);
	}
	
	static void printCityAvgSalary(String cityName) throws Exception {
		CityStatistics city= CityDAO.getCityListByCity(cityName);
		System.out.println("평균연봉");
		System.out.println("도시이름: " + cityName + "이름: " + "평균연봉: " + city.avgSalary );
		System.out.println(city.avgSalary);
	}
	
	static void printCityFirstHireDate(String cityName) throws Exception {
		CityStatistics city = CityDAO.getCityListByCity(cityName);
		System.out.println("도시이름: " + cityName + "입사일: " + city.firstHireDate);
		System.out.println(city.firstHireDate);
	}
	
	static void printCityLastHireDate(String cityName) throws Exception {
		CityStatistics city = CityDAO.getCityListByCity(cityName);
		System.out.println("도시이름: " + cityName + "입사일: " + city.firstHireDate);
		System.out.println(city.lastHireDate);
	}
	
}
