package com.rubypaper.biz.domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
	Entity 생성 후 멤버 초기화를 getter 대신에 생성자를 이용
	( lombok @Data 를 사용하지 않음 )
	
	@AllArgsConstructor, @Access
	@Access(AccessType.FIELD) : getter를 사용하지 않고, 멤버에 직접 접근하겠다 
 */

@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 멤버변수가 있는 생성자. 모든 멤버가 대상 
@ToString(exclude = {"searchCondition", "searchKeyword"})
@Entity
@Table(name = "Employee7", 
	uniqueConstraints = {@UniqueConstraint
			(columnNames = {"NAME","MAILID"})})

@Access(AccessType.FIELD) //
public class Employee7 {
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
	@Column(name = "COMMISSION_PCT", precision = 2, scale = 1,
			columnDefinition = 
			"double CHECK (commission_pct in (10, 12.5, 15, 17.5, 20))")
	private Double commisionPct;
	
	@Transient
	private String searchCondition;

	@Transient
	private String searchKeyword;
}