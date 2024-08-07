package com.rubypaper.biz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "S_PRODUCT")
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 상품 ID
	
	private String name; // 상품 이름
	
	@Column(name = "SHORT_DESC")
	private String shortDesc; // 상품 설명 
	
	private String category;
	
}
