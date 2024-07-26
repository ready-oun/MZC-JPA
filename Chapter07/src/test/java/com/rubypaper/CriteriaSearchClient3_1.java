package com.rubypaper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.Employee2;

public class CriteriaSearchClient3_1 {

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
		
		// FROM Employee emp
		Root<Employee2> emp = criteriaQuery.from(Employee2.class);
		
		// SELECT emp
		criteriaQuery.select(emp);
		
		// JOIN FETCH dmp.dept dept
		emp.fetch("dept", JoinType.LEFT);
		
		// WHERE emp.dept is null
//		criteriaQuery.where(builder.isNull(emp.get("dept")));
		criteriaQuery.where(builder.like(emp.<String>get("mailId"), "%rona%"));
		
		TypedQuery<Employee2> query = em.createQuery(criteriaQuery);
		List<Employee2> resultList = query.getResultList();
		for (Employee2 result : resultList) {
			System.out.println("---> " + result.toString());
		}

		em.close();		

	}

	
	

}
