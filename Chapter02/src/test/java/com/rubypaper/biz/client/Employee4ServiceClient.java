package com.rubypaper.biz.client;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee3;
import com.rubypaper.biz.domain.Employee4;

/**
 *     alter table S_EMP 
       add column mailId varchar(255) not null
 */

public class Employee4ServiceClient {

	public static void main(String[] args) {

		// <persistence-unit name="Chapter02"> 의 설정 정보를 참조
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("Chapter02");
		
		EntityManager em = emf.createEntityManager();
		
		// Generate Entity Transaction
		EntityTransaction tx = em.getTransaction();
		
		try {
			// 영속성 관리 엔터티 생성
			Employee4 employee = new Employee4();
			
			employee.setId(5L);
			employee.setName("홍길동5");
			employee.setMailId("hong");
			employee.setStartDate(new Date());
			employee.setTitle("대리");
			employee.setDeptName("개발부");
			employee.setSalary(2500.00);
			
			// commisionPct의 허용값: 10, 12.5, 15, 17.5, 20
			employee.setCommisionPct(77.8);
			
			// 트랜잭션 시작 
			tx.begin();
			
			// 영속성 관리를 위한 엔터티 등록
			em.persist(employee);
			
			// 트랜잭션 종료
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

}
