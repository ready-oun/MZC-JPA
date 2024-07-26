package com.rubypaper;

import java.util.List;
import java.util.Scanner;

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

public class CriteriaSearchClient3_6 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter07");
		
		try {
			
			// 사용자가 입력한 검색 조건과 검색 단어를 이용한다.
			Scanner keyboard = new Scanner(System.in);
			System.out.println("검색 조건 입력 : name 혹은 mailId");
			String searchCondition = keyboard.nextLine();
			System.out.println("검색어 입력");
			String searchKeyword = keyboard.nextLine();
			
			
			dataSelect(emf, searchCondition, searchKeyword);
			keyboard.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}

	}
	
	private static void dataSelect(EntityManagerFactory emf, String searchCondition, String searchKeyword) {
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Employee2> criteriaQuery = builder.createQuery(Employee2.class);
				
		// FROM Employee e
		Root<Employee2> emp = criteriaQuery.from(Employee2.class);
		
		// SELECT AVG(e.salary)
		criteriaQuery.select(emp);
		
		// JOIN FETCH emp.dept dept
		emp.fetch("dept");
		
		if(searchCondition.equals("mailId")) {
			// WHERE emp.mailId like %searchKeyword%
			criteriaQuery.where(builder.like(emp.<String>get("mailId"), "%" + searchKeyword + "%"));			
		} else if(searchCondition.equals("name")) {
			// WHERE emp.name like %searchKeyword%
			criteriaQuery.where(builder.like(emp.<String>get("name"), "%" + searchKeyword + "%"));
		}
		
		TypedQuery<Employee2> query = em.createQuery(criteriaQuery);
		List<Employee2> resultList = query.getResultList();
		
		if(resultList.size() == 0) {
			System.out.println("No Resuls for Search");
			
		} else {
			for (Employee2 result : resultList) {
				System.out.println("---> " + result.toString());
			} 
		}
		

		em.close();		

	}

	
	

}
