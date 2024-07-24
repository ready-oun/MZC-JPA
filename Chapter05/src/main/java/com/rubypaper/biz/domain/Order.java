package com.rubypaper.biz.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "S_ORD")
public class Order {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 주문 아이디
	
	@Column(name = "CUTOMER_ID")
	private Long customerId;
	
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	
	private Double total; // 주문 금액
	
	@ManyToMany
	@JoinTable(name="S_ITEM", joinColumns = @JoinColumn(name = "ORD_ID"), 
		inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"), uniqueConstraints = @UniqueConstraint(columnNames = {"ORD_ID", "PRODUCT_ID"}))
	private List<Product> productList = new ArrayList<Product>();
	
	// Product 등록할 대, 상품 쪽에 Order 정보도 설정
	public void addProduct(Product product) {
		productList.add(product);
		
		// 반대쪽 Product에도 주문에 대한 참조 정보 설정
		product.getOrderList().add(this);
	}
}
