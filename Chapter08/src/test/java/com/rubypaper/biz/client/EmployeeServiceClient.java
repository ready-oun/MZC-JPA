package com.rubypaper.biz.client;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.service.DepartmentService;
import com.rubypaper.biz.service.EmployeeService;

/**
 * 
 *
 */
public class EmployeeServiceClient {

	public static void main(String[] args) {
		/** Spring Container 생성
		 *  - 개발자가 객체 생성 및 관리를 다른 누군가에게 위임하고 싶음
		 *  -> new 연산자를 사용하지 않게 되어, 약결합으로 구현이 가능해짐 
		 */
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("spring/business-layer.xml");
		/*
		 * spring container에게 객체 생성을 위임하여, 필요한 객체는 불러서 사용하면 됨  
		 */
		DepartmentService deptService = (DepartmentService) container.getBean("deptService");
		
		EmployeeService employeeService = (EmployeeService) container.getBean("empService");
		
		dataInsert(deptService, employeeService);
		
		container.close();
	}
	
	private static void dataInsert(DepartmentService deptService, EmployeeService employeeService) {
		Department department1 = new Department();
		department1.setName("개발부");
		deptService.insertDepartment(department1);
		
		Department department2 = new Department();
		department2.setName("영업부");
		deptService.insertDepartment(department2);
		
		for (int i = 1; i <= 5; i++) {
			Employee employee = new Employee();
			employee.setName("개발직원" + i);
			employee.setSalary(i * 12700.00);
			employee.setMailId("Dev-" + i);
			employee.setDept(department1);
			employeeService.insertEmployee(employee);
		}
		
		for (int i = 1; i <= 7; i++) {
			Employee employee = new Employee();
			employee.setName("영업직원" + i);
			employee.setSalary(i * 24300.00);
			employee.setMailId("Sales-" + i);
			employee.setDept(department2);
			employeeService.insertEmployee(employee);
		}
		
		// Exception in thread "main" org.springframework.beans.factory.BeanDefinitionStoreException: 
		// IOException parsing XML document from class path resource [spring/business-layer.xml]; 
		// nested exception is java.io.FileNotFoundException: 
		// class path resource [spring/business-layer.xml] cannot be opened because it does not exist
	}

}
