package com.rubypaper.biz.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "S_DEPT")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deptId;

	@Column(length = 25, nullable = false)
	private String name;
	
	/**
	 * 지금까지는 단방향 ( Employee -> Department )
	 * 앞으로는 양방향을 위해서 새로운 방향성을 추가 : (Department -> Employee)
	 * => Department class에 Employee를 참조할 수 있는 멤버변수를 추가
	 * => 관계설정은? 한 부서에 여러 사원이 속함 ( 1 : N 관계 )
	 * => 멤버변수의 형태는? 여러 명의 사원정보를 관리 => List 형태의 자료구조가 필요함 
	 * 
	 * mappedBy : 연관관계 소유자 속성 
	 * => 연관관계 : s_emp와 s_dept table의 관계는 FK로 설정되어 있음 
	 * => dept는 FK 정보인데, 현재 FK 설정은 Employee의 dept로 했었음 
	 * => 그래서 mappedBy 에 FK 관계정보인 Employee의 dept 멤버로 설정 ( Entity 기반으로 생각해야 함 )
	 * 
	 * 
	 * 
	 * 
	 *  1. 부서 정보만 검색
	 *  	도메인상 부서정보만 집중해서  
	 */
	@OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
	private List<Employee> empList = new ArrayList<Employee>();
}