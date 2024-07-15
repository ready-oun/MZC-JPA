package com.rubypaper.biz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/**
 * @Column 에 columnDefinition 속성 추가함
 * Double에 대한 precision, scale 설정 테스트
 * 
 * precision : 숫자 관련(소수점) 데이터의 전체 길이
 * scale : 소수점 데이터의 길이 
 * 
 * 엔터티의 멤버가 Double, Float 타입인 경우, precision, scale 설정이 무시됨 (그래서 테이블에 소수점 두 자리가 들어갔음)
 */

@Data
@Entity
@Table(name = "Employee4", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME", "MAILID"}) } )
public class Employee4 {
	@Id// S_EMP 테이블의 PK 와 매핑
	@Column(length = 7, nullable = false)
	private Long id;

	@Column(length = 25, nullable = false)	
	private String name;

	@Column(length = 8, nullable = true)	
	private String mailId;
	
	@Column(name="START_DATE", insertable = false)// 멤버명과 S_EMP 테이블의 칼럼명이 차이
								// 매핑될 칼럼명을 명시적으로 매핑
	private Date startDate;
	
	@Column(length = 25)
	private String title;
	
	@Column(name="DEPT_NAME", length = 30)	
	private String deptName;
	
	@Column(precision = 11, scale = 2)
	private Double salary;	
	
	@Column(name = "COMMISION_PCT", precision = 2, scale = 1,
			columnDefinition = "double CHECK (commision_pct in (10, 12.5, 15, 17.5, 20))")
	private Double commisionPct;
}