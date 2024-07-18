package com.rubypaper.biz.client;

/**
 * 양방향 
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;

public class ManyToOneBothWayClient {
	
	private static void dataSelect(EntityManagerFactory emf) {
	
		EntityManager em = emf.createEntityManager();
		
		Department dept = em.find(Department.class, 1L);
		
		// 부서정보
		System.out.println("부서명: " + dept.getName());
		
		// 사원정보
		System.out.println("=====사원정보 List=====");
		for (Employee emp : dept.getEmpList()) {
			System.out.println(emp.getName());
		}
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("Chapter04");

		try {
			dataSelect(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
		
	}	

}