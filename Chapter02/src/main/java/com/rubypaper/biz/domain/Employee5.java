package com.rubypaper.biz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/*
 * @Temporal
 * 
 * 날짜 데이터 : 날짜 정보 + 시간정보
 * 			- 날짜 정보
 * 			- 시간정보
 * 			- 날짜 정보 + 시간정보
 */

@Data
@Entity
@Table(name = "Employee5", 
	uniqueConstraints = {@UniqueConstraint
			(columnNames = {"NAME","MAILID"})})
public class Employee5 {
	@Id// S_EMP 테이블의 PK 와 매핑
	@Column(length = 7, nullable = false)
	private Long id;
	
	@Column(length = 25, nullable = false)
	private String name;
	
	@Column(length = 8, unique = true)
	private String mailId;
	
	@Column(name="START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(length = 25)
	private String title;
	
	@Column(name="DEPT_NAME", length = 30)	
	private String deptName;
	
	@Column(precision = 11, scale = 2)
	private Double salary;	
	
	//@Column(name = "COMMISITION_PCT", precision = 2, scale = 1)
	@Column(name = "COMMISITION_PCT", precision = 2, scale = 1,
			columnDefinition = 
			"double CHECK (commisition_pct in (10, 12.5, 15, 17.5, 20))")
	private Double commisionPct;
}