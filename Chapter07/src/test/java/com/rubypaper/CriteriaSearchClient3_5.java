package com.rubypaper;

import java.util.List;
import javax.persistence.criteria.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.Employee2;

public class CriteriaSearchClient3_5 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter07");
		
		try {
			dataSelect(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}

	}
	
	private static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Employee2> criteriaQuery = builder.createQuery(Employee2.class);
		
		/** Subquery */
		Subquery<Double> subquery = criteriaQuery.subquery(Double.class);
		
		// FROM Employee e
		Root<Employee> e = subquery.from(Employee.class);
		
		// SELECT AVG(e.salary)
		subquery.select(builder.avg(e.<Double>get("salary")));
		
		/* Main Query  */
		// FROM Employee emp
		Root<Employee2> emp = criteriaQuery.from(Employee2.class);
		
		// SELECT emp
		criteriaQuery.select(emp);
		
		// JOIN FETCH emp.dept dept
		emp.fetch("dept");
		
		
		/* 메인쿼리에 서브쿼리 연결하기 */
		// WHERE salary >= (서브쿼리)
		criteriaQuery.where(builder.ge(emp.<Double>get("salary"), subquery));
			
		
		TypedQuery<Employee2> query = em.createQuery(criteriaQuery);
		List<Employee2> resultList = query.getResultList();
		for (Employee2 result : resultList) {
			System.out.println("---> " + result.toString());
		}

		em.close();		

	}

	
	

}
