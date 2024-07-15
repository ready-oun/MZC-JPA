package com.rubypaper.biz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/**
 * @Table 에 uniqueConstraints 속성 추가
 * NAME, MAILID 두 컬럼 대상으로 unique 제약조건 생성 
 * 
 * 1. 테이블에 uniqueConstraints 생성유무 확인 ( 내가 원하는 컬럼 기준 )
 * 		NAME, MAILID의 두 개 컬럼으로 복합 제약조건이 생성됐는지 확인  
 * 2. uniqueConstraints 동작 테스트
 * 3. 테스트 데이터 생성 
 * 		hong, 홍길동 : unique 제약 조건 위배
 * 		hong2, 홍길동 : 
 * 
 * 그래서, 정말 중요한 건 "생성되는 데이터가 처음부터 깨끗하게 만들어지지 않는다면, 향후 개발 중에 엉뚱한 데이터가 나오게 됨"
 * 진리 : 들어가는 것이 깨끗해야 나오는 게 깨끗하다
 */

@Data
@Entity
@Table(name = "Employee3", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME", "MAILID"}) } )
public class Employee3 {
	@Id// S_EMP 테이블의 PK 와 매핑
	private Long id;
	
	private String name;
	
	private String mailId;
	
	@Column(name="START_DATE")// 멤버명과 S_EMP 테이블의 칼럼명이 차이
								// 매핑될 칼럼명을 명시적으로 매핑
	private Date startDate;
	
	private String title;
	
	@Column(name="DEPT_NAME")	
	private String deptName;
	
	private Double salary;	
	
	@Column(name = "COMMISITION_PCT")
	private Double commisionPct;
}