package com.rubypaper.biz.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee1;
import com.rubypaper.biz.domain.Employee2;

/*
	refresh() 메소드 이용한 에티티 갱신 실습
	
	테이블의 변화를 entity에 반영
	테이블의 변화는 내가 누군가가 table의 데이터를 수정한 경우,
	application에서 사용 중인 entity를 table 기준으로 갱신
	
	<property name="hibernate.hbm2ddl.auto" value="update" />를 create로 수정 

	
 */

public class Employee2ServiceClient10 {

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
				// 기존에 있는 데이터를 기준으로 식별자를 지정 
				employee.setId(1L);
				employee.setName("이름수정");
				
				tx.begin();
				em.persist(employee); 
				tx.commit();
				
				// 일정 시간 대기 -> for문으로 시간벌기 -> 그동안 직접 DB에 가서 수정함  
				// 직접 table의 데이터 수정 ( 제3자가 데이터 수정하는 걸 가정 ) 
				for ( int i = 0; i< 30; i++) {
					Thread.sleep(1000); // 1초를 30번 
					System.out.println("다른 사용자가 데이터 수정 중 ... ");
				}
				
				// 현재 사용 중인 entity를 DB 기준으로 갱신 
				em.refresh(employee);
				System.out.println("갱신된 사원 정보: " + employee.toString());

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}
