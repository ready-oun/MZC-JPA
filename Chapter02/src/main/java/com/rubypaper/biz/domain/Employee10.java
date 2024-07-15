package com.rubypaper.biz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;


@Data
@Entity
@Table(name = "Employee10")
@TableGenerator(name = "SEQ_GENERATOR", // generator 이름  
				table = "SHOPPING_SEQUENCE", // table 명
				pkColumnName= "SEQ_NAME", // column 명 
				pkColumnValue= "EMP_SEQ", // SEQ_NAME 컬럼명의 값이 EMP_SEQ
				valueColumnName = "NEXT_VALUE", // column명 
				initialValue = 0,
				allocationSize = 1)

public class Employee10 {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, //사용할 전략을 시퀀스로  선택
				generator = "SEQ_GENERATOR") //식별자 생성기를 설정해놓은 Employee9_GENERATOR로 설정 
	private Long id;
	
	private String name;
}