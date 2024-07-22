package com.seventeam.hr.util;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.seventeam.hr.dao.DepartmentDAO;
import com.seventeam.hr.dao.EmpDAO;
import com.seventeam.hr.dao.LocationDAO;
import com.seventeam.hr.domain.Department;
import com.seventeam.hr.domain.Emp;
import com.teamseven.hr.statistics.DepartmentStatistics;
import com.teamseven.hr.statistics.LocationStatistics;

public class StaticsPrinter {
	// 사원검색시 정보 출력
	public static void printEmpListByName() {
		while (true) {
			try {
				System.out.println("사원 이름을 입력해주세요, 초기화면으로 돌아가려면 '끝'을 입력해주세요.");
				Scanner sc = new Scanner(System.in);
				System.out.print("사원이름: ");
				String empName = sc.nextLine();

				if (empName.equals("끝")) {
					break;
				} else {
					List<Emp> list = EmpDAO.getEmpListByName(empName);
					for (Emp emp : list) {
						System.out
								.println("---------------------------------------------------------------------------");
						System.out.println("연봉: " + emp.getSalary() + ", 입사일: " + emp.getHireDate() + ", 부서장번호"
								+ emp.getManagerId() + ", 부서번호 " + emp.getDepartmentId());
						System.out
								.println("---------------------------------------------------------------------------");
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("---------------------------------------------------------------------------");
				System.err.println("JDBC 드라이버 로드 중 오류 발생" + e);
				System.out.println("---------------------------------------------------------------------------");

			} catch (NumberFormatException e) {// String -> int 형변환 문제

				System.out.println("---------------------------------------------------------------------------");
				System.out.println("숫자형식의 텍스트가 아닙니다.");
				System.out.println("---------------------------------------------------------------------------");

			} catch (InputMismatchException e) {// 사용자가 정수 대신 다른 형식을 입력했을때

				System.out.println("---------------------------------------------------------------------------");
				System.out.println("잘못된 형태로 입력하셨습니다. 올바른 값으로 다시 입력해주세요.");
				System.out.println("---------------------------------------------------------------------------");

			} catch (NoSuchElementException e) { // 입력이 null 일때

				System.out.println("---------------------------------------------------------------------------");
				System.out.println("검색되지 않습니다.");
				System.out.println("---------------------------------------------------------------------------");

			} catch (Exception e) {// 모든 예외

				System.out.println("---------------------------------------------------------------------------");
				System.out.println(e.getMessage());
				System.out.println("---------------------------------------------------------------------------");

			}
		}
	}

	// 입사 관련 출력
	public static void printInfoByHireYear() {
		while (true) {
			try {

				Scanner sc = new Scanner(System.in);
				System.out.println("입사년도를 입력해주세요 처음화면으로 되돌아가려면 '끝'을 입력해주세요.");
				System.out.print("입사년도: ");
				String hireYearStr = sc.nextLine();
				if (hireYearStr.equals("끝")) {
					break;
				}
				int hireYear = Integer.parseInt(hireYearStr);
				HireYear.getYearStatics(hireYear);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("---------------------------------------------------------------------------");
				System.err.println("JDBC 드라이버 로드 중 오류 발생" + e);
				System.out.println("---------------------------------------------------------------------------");

			} catch (NumberFormatException e) {// String -> int 형변환 문제

				System.out.println("---------------------------------------------------------------------------");
				System.out.println("숫자형식의 텍스트가 아닙니다.");
				System.out.println("---------------------------------------------------------------------------");

			} catch (InputMismatchException e) {// 사용자가 정수 대신 다른 형식을 입력했을때

				System.out.println("---------------------------------------------------------------------------");
				System.out.println("잘못된 형태로 입력하셨습니다. 올바른 값으로 다시 입력해주세요.");
				System.out.println("---------------------------------------------------------------------------");

			} catch (NoSuchElementException e) { // 입력이 null 일때

				System.out.println("---------------------------------------------------------------------------");
				System.out.println("검색되지 않습니다.");
				System.out.println("---------------------------------------------------------------------------");

			} catch (Exception e) {// 모든 예외

				System.out.println("---------------------------------------------------------------------------");
				System.out.println(e.getMessage());
				System.out.println("---------------------------------------------------------------------------");

			}
		}
		System.out.println("처음으로 돌아갑니다.");
	}

	// 부서번호 관련 통계 출력
	public static void printInfoByDpt(int dptId) throws Exception {
		List<Department> result = DepartmentDAO.getDptListByDptId(dptId);

		for (Department dpt : result) {
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("부서 ID: " + dpt.getDepartmentId() + " 부서 이름: " + dpt.getDepartmentName() + " 매니저 아이디 : "
					+ dpt.getManagerId() + " 지역 아이디: " + dpt.getLocationId());
			System.out.println("---------------------------------------------------------------------------");
		}

	}

	public static void printCountByDpt(int dptId) throws Exception {
		DepartmentStatistics result = DepartmentDAO.getDptCountByDptId(dptId);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("부서 총 개수: " + result.getCount());
		System.out.println("---------------------------------------------------------------------------");
	}

	public static void printMaxSalaryByDpt(int dptId) throws Exception {
		DepartmentStatistics result = DepartmentDAO.getDptMaxSalaryByDptId(dptId);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("부서내 최대 연봉자: " + result.getName() + " 연봉: " + result.getMaxSalary());
		System.out.println("---------------------------------------------------------------------------");
	}

	public static void printMinSalaryByDpt(int dptId) throws Exception {
		DepartmentStatistics result = DepartmentDAO.getDptMinSalaryByDptId(dptId);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("부서내 최소 연봉자: " + result.getName() + " 연봉: " + result.getMinSalary());
		System.out.println("---------------------------------------------------------------------------");
	}

	public static void printAvgSalaryByDpt(int dptId) throws Exception {
		DepartmentStatistics result = DepartmentDAO.getDptAvgSalaryByDptId(dptId);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("부서 평균 연봉: " + result.getAvgSalary());
		System.out.println("---------------------------------------------------------------------------");
	}

	public static void printFirstEmpByDpt(int dptId) throws Exception {
		List<DepartmentStatistics> result = DepartmentDAO.getDptFirstEmpByDptId(dptId);
		System.out.println("---------------------------------------------------------------------------");
		for (DepartmentStatistics dptStatic : result) {
			System.out.println("부서내 입사일이 가장 빠른사람 : " + dptStatic.getName());
		}
		System.out.println("---------------------------------------------------------------------------");
	}

	// 도시 정보 출력
	public static void printCityCount() throws Exception {
		int count = LocationDAO.getCityCoutByCity();
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("전체 도시 개수" + count);
		System.out.println("---------------------------------------------------------------------------");
	}

	public static void printCityMaxSalary(String cityName) throws Exception {
		LocationStatistics city = LocationDAO.getCityListByCity(cityName);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("도시이름: " + cityName + " 최대연봉: " + city.getMaxSalary());
		System.out.println("---------------------------------------------------------------------------");
	}

	public static void printCityMinSalary(String cityName) throws Exception {
		LocationStatistics city = LocationDAO.getCityListByCity(cityName);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("도시이름: " + cityName + " 최저연봉: " + city.getMinSalary());
		System.out.println("---------------------------------------------------------------------------");
	}

	public static void printCityAvgSalary(String cityName) throws Exception {
		LocationStatistics city = LocationDAO.getCityListByCity(cityName);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("도시이름: " + cityName + " 평균연봉: " + city.getAvgSalary());
		System.out.println("---------------------------------------------------------------------------");
	}

	public static void printCityFirstHireDate(String cityName) throws Exception {
		LocationStatistics city = LocationDAO.getCityListByCity(cityName);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("도시이름: " + cityName + " 입사일: " + city.getFirstHireDate());
		System.out.println("---------------------------------------------------------------------------");
	}

	public static void printCityLastHireDate(String cityName) throws Exception {
		LocationStatistics city = LocationDAO.getCityListByCity(cityName);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("도시이름: " + cityName + " 입사일: " + city.getLastHireDate());
		System.out.println("---------------------------------------------------------------------------");
	}

	// 부서장 정보 출력
	public static void printEmpInfoByManagerName() {
		Scanner sc = new Scanner(System.in);
		System.out.print("부서장 이름: ");
		String managerName = sc.nextLine();

		while (!managerName.equals("끝")) {
			try {
				List<Emp> list = EmpDAO.getEmpInfoByManagerName(managerName);
				System.out.println("---------------------------------------------------------------------------");
				for (Emp emp : list) {
					System.out.println("사원이름: " + emp.getName() + ", 연봉: " + emp.getSalary() + ", 입사일: "
							+ emp.getHireDate() + ", 부서명: " + emp.getDepartmentName());
				}
				System.out.println("---------------------------------------------------------------------------");
				System.out.print("부서장 이름: ");
				managerName = sc.nextLine();
			} catch (Exception e) {
				System.out.println(e.getMessage() + ". 다시 입력하세요.");
				System.out.print("부서장 이름: ");
				managerName = sc.nextLine();
			}
		}
		System.out.println("종료되었습니다.");
	}

	// 부서장의 부서 직원들 정보 출력
	public static void printEmpInfoByManager() {
		Scanner sc = new Scanner(System.in);
		System.out.print("부서장 이름: ");
		String managerName = sc.nextLine();

		while (!managerName.equals("끝")) {
			try {
				List<Emp> list = EmpDAO.getEmpInfoByManagerName(managerName);
				System.out.println("---------------------------------------------------------------------------");
				for (Emp emp : list) {
					System.out.println("사원이름: " + emp.getName() + ", 연봉: " + emp.getSalary() + ", 입사일: "
							+ emp.getHireDate() + ", 부서명: " + emp.getDepartmentName());
				}
				System.out.println("---------------------------------------------------------------------------");
				System.out.print("부서장 이름: ");
				managerName = sc.nextLine();
			} catch (Exception e) {
				System.out.println(e.getMessage() + ". 다시 입력하세요.");
				System.out.print("부서장 이름: ");
				managerName = sc.nextLine();
			}

		}
		System.out.println("처음으로 돌아갑니다.");
	}

	public static void printTeam() {
		String team = " .----------------.   .----------------.  .----------------.  .----------------.  .----------------. \r\n"
				+ "| .--------------. | | .--------------. || .--------------. || .--------------. || .--------------. |\r\n"
				+ "| |   _______    | | | |  _________   | || |  _________   | || |      __      | || | ____    ____ | |\r\n"
				+ "| |  |  ___  |   | | | | |  _   _  |  | || | |_   ___  |  | || |     /  \\     | || ||_   \\  /   _|| |\r\n"
				+ "| |  |_/  / /    | | | | |_/ | | \\_|  | || |   | |_  \\_|  | || |    / /\\ \\    | || |  |   \\/   |  | |\r\n"
				+ "| |      / /     | | | |     | |      | || |   |  _|  _   | || |   / ____ \\   | || |  | |\\  /| |  | |\r\n"
				+ "| |     / /      | | | |    _| |_     | || |  _| |___/ |  | || | _/ /    \\ \\_ | || | _| |_\\/_| |_ | |\r\n"
				+ "| |    /_/       | | | |   |_____|    | || | |_________|  | || ||____|  |____|| || ||_____||_____|| |\r\n"
				+ "| |              | | | |              | || |              | || |              | || |              | |\r\n"
				+ "| '--------------' | | '--------------' || '--------------' || '--------------' || '--------------' |\r\n"
				+ " '----------------'   '----------------'  '----------------'  '----------------'  '----------------'";
		System.out.println(team);

	}
}
