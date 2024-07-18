package com.rubypaper.biz.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;

public class ManyToOneOneWayClient {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("Chapter04");

		try {
			// 부서 등록, 사원 등록
//			dataInsert(emf);
//			dataSelect(emf);
//			dataUpdate(emf);
			dataDelete(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
		
	}
	
	// 엔티티 삭제
	private static void dataDelete(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		/*
		 * @ManyToOne 관계에서 '부서'를 삭제하는 거임 
		 * 1. 부서 삭제 시, 삭제되는 부서를 참조하는 데이터가 있음 (s_emp의 데이터들이겠지)
		 * 2. 부서를 참조하고 있는 데이터에 대해 참조관계를 끊으면 됨
		 * 	  사원 정보의 부서 정보는 없는 상태가 됨 (null)
		 * *데이터베이스에서 참조 무결성이 무엇인지 대답할 수 있어야 한다. 
		 *  Referential integrity constraint violation : 참조무결성 위배 
		 *  ㄴ 3L 부서에 소속된 사원이 있음
		 *  
		 * 3. Employee class의 dept 멤버에 optional 속성에 대한 설정이 '삭제'와 문제가 없는지 고려
		 * 		
		 *    @ManyToOne (optional=false)
		 *    - optional = false -> inner join 이 생성됨 
		 *    - 조건에서 null인 것은 제외함 -> 대상은 null이 아닌 것
		 *    	=> 즉, null이면 안 됨 그래서 에러가 남 : RollbackException: Error while committing the transaction 
		 *    
		 *    optional = false를 optional = true로 변경해야 함 ( outer join 이어야 null 값을 포함하잖음 ) 
		 *    
		 *     
		 *  
		 * 4. JPA를 떠나서, 테이블 관계상에서 삭제 시 cascade 옵션을 이용해 참조되는 데이터까지 함께 삭제가 되도록 하려면 
		 *    어떻게 해야 함?
		 *  
		 */
		
		// 3L 부서에 소속된 사원 정보를 영속성 컨테이너에서 검색, 부서정보를 지움
		Employee emp = em.find(Employee.class, 1L);
		// 3L -> null 
		// s_emp와 s_dept의 참조 관계를 끊음 
		emp.setDept(null); 
		
		Department dept = em.find(Department.class, 3L);
		// 영속성 컨테이너에서 해당 엔티티 삭제
		em.remove(dept);
		
		tx.commit();
	}
	
	// 엔티티 수정
	private static void dataUpdate(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		// 사원의 부서 이동 시나리오
		// 신규 부서 등록 
		Department dept = new Department();
		dept.setName("기술영업");
		em.persist(dept);
		
		// 사원의 부서 이동(변경)
		Employee emp = em.find(Employee.class, 1L);
		// 현재 사원번호 1번의 사원은 개발부 소속임
		emp.setDept(dept);
		tx.commit();
	}
	
	// 엔티티 조회
	
	private static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		Employee emp = em.find(Employee.class, 1L);
		
		/** ManyToOne 관계에서 조회하는 경우 
		 * 1. 기본
		 * 		outer join
		 * 2. optional = false
		 * 		inner join
		 * 3. fetch = FetchType.LAZY
		 * 		3.1. 테이블 하나 (S_EMP)로만 조회
		 * 			employee의 멤버로만 사용 => S_EMP만 조회하면 가능
		 * 		3.2. 두 테이블(S_EMP, S_DEPT) 모두 조회
		 * 			employee, department 멤버 모두 사용
		 * 			=> S_EMP, S_DEPT 두 테이블 모두 사용해야만 조회 가능
		 *
		 * 4. fetch = FetchType.EAGER 
		 * 		기본적으로 두 테이블(S_EMP, S_DEPT) 모두 사용
		 * 		즉, employee의 멤버만 사용해도 S_EMP, S_DEPT 두 테이블 모두 사용 
		 */
		
		/*
		 * 결과: S_EMP table만 조회가 됨
		 * 
		 * emp.getName()은 employee만 해당 
		 */
		
//		System.out.println(emp.getName());
		
		/*
		 * 결과: S_EMP, S_DEPT가 모두 조회가 됨
		 * employee가 필요하지만, department 타입의 멤버까지 사용 
		 *  
		 */
		
//		System.out.println(emp.toString());
//		System.out.println(emp.toString() + " - " + emp.getDept().toString());
		
		/*
		 * 실행 결과는 left outer join 임.
		 * 
		 * 현재의 도메인은 사원관리임.
		 * - 신규 사원인 경우에 교육인 끝나기 전까지는 부서배치를 보유
		 *   => 해당 사원의 경우 부서정보가 없을 수 있음.
		 *   
		 * - 근무중에 심각한 문제를 일으킨 경우
		 *   => 부서에서 제외하고, 향후에 부서를 재배치.
		 *      그래서, 부서정보가 없음.
		 * 
		 * 따라서, 기본적으로 전체 사원정보가 조회되도록 하도록 하면,
		 * outer join 이 필요하게 됨.
		 * 
		 * 그리고, 필요에 따라서 inner join 이 되도록 설정해서 사용하면 됨.
		 * 
		 */
	}
	
	private static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction ex = em.getTransaction();
		
		ex.begin();
		
		// 부서 객체 생성
		Department dept = new Department();
		dept.setName("개발부");
		em.persist(dept);//영속성 컨테이너에 엔터티 등록
		
		// 사원 객체 생성
		Employee emp1 = new Employee();
		emp1.setName("홍길동");
		emp1.setDept(dept);
		em.persist(emp1);
		
		Employee emp2 = new Employee();
		emp2.setName("김길동");
		emp2.setDept(dept);
		em.persist(emp2);
		
		ex.commit();
		em.close();
	}

}