package com.rubypaper.shopping.biz.domain;

import javax.persistence.Embeddable;

import lombok.Data;
/*
 * Entity class �� �ƴ�.
 * �ٸ�  entity�� ���ԵǴ� Ŭ����. => @Embeddable
 */

@Embeddable
@Data
public class Address {

	// ����
	private String city;		

	// ���θ�
	private String roadName;		

	// ������ȣ
	private String zipCode;		
}