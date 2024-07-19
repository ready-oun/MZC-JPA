package com.rubypaper.biz.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.EmployeeCard;


public class OneToOnebBothWayClient {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter05");

		try {
			dataInsert(emf);
			dataSelect(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}
	
	private static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		// 검색된 사원증 통해 직원 정보 사용
//		EmployeeCard employeeCard = em.find(EmployeeCard.class, 1L);
		
		Employee employee = em.find(Employee.class, 1L);
		System.out.println("직원을 통한 사원증 정보 접근: " + employee.getCard().toString());
		
		// toString() -> StackOverflowError : Exception in thread "main" java.lang.StackOverflowError ( p. 304 ) 
		// System.out.println("사원증을 통한 직원 정보 접근: " + employeeCard.toString());
		
		System.out.println("사원증 유효기간 : " + employeeCard.getExpireDate());
		System.out.println("사원증 소유자 : " + employeeCard.getEmployee().getName());
		
		// 검색된 직원을 통해 사원증 정보 사용 
		Employee employee = em.find(Employee.class, 1L);
		System.out.println("사원증 소유자: " + employee.getName());
		// employee.getCard()가 가능하도록 @OneToOne(mappedBy="employee") private EmployeeCard card를 employee 객체 안에 추가했음 
		System.out.println("사원증 유효기간 : " + employee.getCard().getExpireDate()); 
		
//		EmployeeCard employeeCard = em.find(EmployeeCard.class, 1);
//		System.out.println("검색된 사원증 번호 : " + employeeCard.getCardId());
//		System.out.println("권한 : " + employeeCard.getRole());
//		System.out.println("사원증 소유자 : " + employeeCard.getEmployee().getName());
		
		em.close();
	}
	
	private static void dataInsert(EntityManagerFactory emf) throws ParseException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// 직원 등록
		Employee employee = new Employee();
		employee.setName("둘리");
		// 사원증에 대한 참조 설정
		employee.setEmployeeCard(card);
		em.persist(employee);
		
		// 사원증 등록
		EmployeeCard card = new EmployeeCard();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		card.setExpireDate(dateFormat.parse("2025-12-31"));
		card.setRole("MASTER");
		
		// 직원에 대한 참조 설정
		card.setEmployee(employee);
		
		em.persist(card);
		
		em.getTransaction().commit();
		em.close();
		
		System.out.println("사원증을 통한 직원 정보 접근 : " + card.getEmployee().getName()); // 사원증 -> 직원 
		System.out.println("직원을 통한 사원증 정보 접근 : " + employee.getCard().getExpireDate()); // 직원 -> 사원증 
	}

}
