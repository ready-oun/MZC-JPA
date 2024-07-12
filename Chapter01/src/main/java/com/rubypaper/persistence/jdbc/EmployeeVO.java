package com.rubypaper.persistence.jdbc;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class EmployeeVO {
	private Long id; // 직원ID
	private String name; // 직원이름 
	private Timestamp startDate; // 입사일
	private String title; // 직급 
	private String deptName; // 부서명 
	private Double salary; // 급여 

}
