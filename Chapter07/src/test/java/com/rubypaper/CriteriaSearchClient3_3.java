package com.rubypaper;

import java.util.List;
import javax.persistence.criteria.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.Employee2;

public class CriteriaSearchClient3_3 {

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
		
		// SELECT emp.dept.name, SUM(emp.salary), COUNT(emp), AVG(emp.salary)
		criteriaQuery.multiselect(emp.<String>get("dept").get("name"), 
				builder.sum(emp.<Double>get("salary")),
				builder.count(emp),
				builder.avg(emp.<Double>get("salary")));
		
		// GROUP BY emp.dept.name
		criteriaQuery.groupBy(emp.get("dept").get("name"));
		
		// HAVING COUNT(emp) >= 3
		criteriaQuery.having(builder.ge(builder.count(emp), 3));
		
		TypedQuery<Employee2> query = em.createQuery(criteriaQuery);
		List<Employee2> resultList = query.getResultList();
		for (Employee2 result : resultList) {
			System.out.println("---> " + result.toString());
		}

		em.close();		

	}

	
	

}