package com.rubypaper.biz.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee1;
import com.rubypaper.biz.domain.Employee2;

/*
	p.188
	분리상태에서의 엔티티 수정 
	persistence.xml에서 <property name="hibernate.hbm2ddl.auto" value="create" /> 로 수정 ( update -> create )
 */

public class Employee2ServiceClient6 {

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
				employee.setName("홍길동");
				
				tx.begin();
				em.persist(employee);
				
				/*
				 * 묵시적 flush 실행 => insert 생성됨
				 * 
				 * persistence.xml 의 테이블이 생성되는 옵션
				 * DB의 테이블에도 없는 데이터  
				 * 영속성 컨테이너에 없는 엔티티임 
				 */
				tx.commit(); 
				
				// 엔티티 분리
				em.clear();
				
				// 준영속 엔티티 수정
//				tx.begin();
//				employee.setName("이름수정");
//				tx.commit();

				// merge() 이용해서 수정 
				tx.begin();
				employee.setName("이름수정"); // select가 여기서 발생함 ( 식별자로 조회 ) 
				// 매개변수: 준영속 상태의 엔티티
				// 반환결과: 영속 상태의 엔티티 
				Employee2 mergedEmp = em.merge(employee);
				
				/**
				 * 1. 준영속 상태의 entity 식별자를 이용해서 1차 캐시에서 확인 후 없으면,
				 * 	   식별자를 이용해서 DB에서 조회 => select 전송됨
				 * 
				 *    1차 캐시에 엔티티 등록 
				 * 2. 준영속 상태의 엔티티를 1차 캐시에 반영 
				 * 	   스냅샷과 차이가 발생 
				 * 
				 * 3. commit() -> 묵시적 flush 발생 
				 * 4. 스냅샷과의 차이를 DB에 반영하기 위해서 update sql이 작성됨 
				 */
				
				tx.commit(); 
				
				System.out.println("employee의 상태 " + em.contains(employee));
				System.out.println("employee의 상태 " + em.contains(mergedEmp));
				
				// 분리된 entity를 수정하려면 어떻게 해야..? 
				// Employee2ServiceClient6로 계속됨 

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}
