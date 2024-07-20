import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HireYear {

	public static void getYearStatics(int hireYear) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1. 해당년도의 입사자 총원, 2. 해당년도의 평균연봉, 3. 해당년도의 최대연봉 4. 해당년도의 최소연봉 5. 해당년도에 가장 빠른 입사자" );
		System.out.print("궁금한 정보: " );
		String num = sc.nextLine();
		
		if(num.equals("1")) {
			try { 
				List<EmpStatistics> result = EmpDAO.getInfoByHireYear(hireYear);
				System.out.println("해당년도에 입사자 수: " + result.get(0).count);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}else if(num.equals("2")) {
			
			try { 
				List<EmpStatistics> result = EmpDAO.getInfoByHireYear(hireYear);
				System.out.println("해당년도에 평균연봉: " + result.get(0).avgSalary);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}else if(num.equals("3")) {			
			
			try { 
				List<EmpStatistics> result = EmpDAO.getMaxSalaryByYear(hireYear);
				System.out.println("해당년도에 최고연봉: " + result.get(0).maxSalary);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}else if(num.equals("4")) {
			
			try { 
				List<EmpStatistics> result = EmpDAO.getMinSalaryByYear(hireYear);
				System.out.println("해당년도에 평균연봉: " + result.get(0).minSalary);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}else if(num.equals("5")) {
			
			try { 
				List<EmpStatistics> result = EmpDAO.getEmpByYear(hireYear);
				System.out.println("해당년도에 가장 빨리 입사한 사람의 정보 ["+ "이름: " + result.get(0).name +", 사번: " + result.get(0).employeeId+ ", 입사일: " + result.get(0).hireDate + ", 직업ID: " + result.get(0).jobId + ", 연봉: " + result.get(0).salary + "]");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
		}else {
			
		}
		
	}

	
	
}
