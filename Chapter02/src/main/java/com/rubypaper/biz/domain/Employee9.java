package com.rubypaper.biz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/*
 * 식별자
 * 
 * JPA 가 관리하는 엔터티는 @Id로 지정한 식별자 변수를 통해서 식별되었음.
 * 테이블의 기본키와 엔터티의 식별자 변수를 매핑해서 유일한 엔터티 객체를 식별하고
 * 관리.
 * 
 * 식별자 생성 전략( 중요 )
 * p.118 참고
 * 
 * Sequence 사용한 실습
 * 
 */

@Data
@Entity
@Table(name = "Employee9")
@SequenceGenerator(name = "Employee9_GENERATOR",
					sequenceName = "Employee9_sequence",
					initialValue = 1,//sequence 초기값
					allocationSize = 50)//sequecne 의 할당값 (메모리를 통해 할당할 범위 사이즈)
public class Employee9 {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
				generator = "Employee9_GENERATOR") //식별자 생성기를 설정해놓은 Employee9_GENERATOR로 설정 
	private Long id;
	
	private String name;
}