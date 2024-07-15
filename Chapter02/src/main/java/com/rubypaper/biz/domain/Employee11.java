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
@Table(name = "Employee11")
public class Employee11 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //사용할 전략을 AUTO로 선택
	private Long id;
	
	private String name;
}