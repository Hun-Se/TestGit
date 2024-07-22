package com.seventeam.hr.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.seventeam.hr.dao.EmpDAO;
import com.seventeam.hr.statistics.EmpStatistics;

public class HireYear {

	public static void getYearStatics(int hireYear) throws Exception {
			Scanner sc = new Scanner(System.in);
			System.out.println("1. 해당년도의 입사자 총원, 2. 해당년도의 평균연봉, 3. 해당년도의 최대연봉 4. 해당년도의 최소연봉 5. 해당년도에 가장 빠른 입사자");
			System.out.print("궁금한 정보: ");
			int num = sc.nextInt();
	
			if (num == 1) {
				List<EmpStatistics> result = EmpDAO.getInfoByHireYear(hireYear);
				System.out.println("---------------------------------------------------------------------------");
				System.out.println("해당년도에 입사자 수: " + result.get(0).getCount());
				System.out.println("---------------------------------------------------------------------------");
			} else if (num == 2) {
				List<EmpStatistics> result = EmpDAO.getInfoByHireYear(hireYear);
				System.out.println("---------------------------------------------------------------------------");
				System.out.println("해당년도에 평균연봉: " + result.get(0).getAvgSalary());
				System.out.println("---------------------------------------------------------------------------");
			} else if (num == 3) {	
				List<EmpStatistics> result = EmpDAO.getMaxSalaryByYear(hireYear);
				System.out.println("---------------------------------------------------------------------------");
				System.out.println("해당년도에 최고연봉: " + result.get(0).getMaxSalary());
				System.out.println("---------------------------------------------------------------------------");
			} else if (num == 4) {
				List<EmpStatistics> result = EmpDAO.getMinSalaryByYear(hireYear);
				System.out.println("---------------------------------------------------------------------------");
				System.out.println("해당년도에 평균연봉: " + result.get(0).getMinSalary());
				System.out.println("---------------------------------------------------------------------------");
			} else if (num == 5) {
				List<EmpStatistics> result = EmpDAO.getEmpByYear(hireYear);
				System.out.println("---------------------------------------------------------------------------");
				System.out.println("해당년도에 가장 빨리 입사한 사람의 정보 [" + "이름: " + result.get(0).getName() + ", 사번: "
						+ result.get(0).getEmployeeId() + ", 입사일: " + result.get(0).getHireDate() + ", 직업ID: "
						+ result.get(0).getJobId() + ", 연봉: " + result.get(0).getSalary() + "]");
				System.out.println("---------------------------------------------------------------------------");
			} else {
				System.out.println("---------------------------------------------------------------------------");
				System.out.println("잘못된 번호를 입력했습니다. 1~5의 번호를 선택해주세요.");
				System.out.println("---------------------------------------------------------------------------");
			}
		}

	}
