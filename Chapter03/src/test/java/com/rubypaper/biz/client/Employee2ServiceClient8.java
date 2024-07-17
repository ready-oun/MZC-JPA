package com.rubypaper.biz.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee1;
import com.rubypaper.biz.domain.Employee2;

/*
	p.194
	merge() 메소드 이용해서 persist()처럼 동작되도록 실습 
	
	persist() => insert 가 발생
	
	merge() ====> 
 */

public class Employee2ServiceClient8 {

	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("Chapter03");

		EntityManager em = emf.createEntityManager();

		// 커밋할 때만 flush 가 동작하게 됨.
		em.setFlushMode(FlushModeType.COMMIT);

		// 엔터티 트랜잭션 생성
		EntityTransaction tx = em.getTransaction();

		try {
				Employee2 employee = new Employee2();
				
				// 식별자를 직접 설정 
				employee.setId(1L); // 컨테이너에 엔티티가 하나도 없는 경우에 항상 select가 동일하게 발생하는데,
									// 지금도 비슷한 상황임. 1L이라는 식별자값이 1차 캐시에 있는지 확인한 것임. 
				employee.setName("홍길동");
				
				tx.begin();
				// employee 엔티티는 생성 상태
				/* merge
				 * 1. 식별자가 있으므로 1차 캐시에서 먼저 검색 
				 * 2. DB에 식별자 값으로 검색 => select 작성 및 전송 
				 * 3. DB에도 없는 엔티티임을 확인 
				 * 4. DB에 등록(insert 작성 및 전송)
				 */
				em.merge(employee); // persist 자리에 merge만 넣어보았음 
				tx.commit(); 
				

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}
