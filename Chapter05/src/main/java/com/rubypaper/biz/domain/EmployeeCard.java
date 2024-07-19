package com.rubypaper.biz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude="employee")
@Entity
@Table(name= "S_EMP_CARD")
public class EmployeeCard {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "CARD_ID")
	private Long cardId; // 사원증 아이디 
	
	@Column(name= "EXPIRE_DATE")
	private Date expireDate; // 사원증 만료기간 
	
	private String role; //권한 
	
//	@OneToOne (optional = false, fetch = FetchType.LAZY)// 일대일 설정해서 지금은 하나의 사원증을 한 명의 직원만 소유할 수 있게 됨 : 비즈니스 변경하여 한의 사원증을 여러 명의 직원이 소유하도록 변경한다면 다대일 관계로 수정해야 함 
//	@JoinColumn(name = "EMP_CARD_ID")
	
	@OneToOne(mappedBy = "card")
	private Employee employee;

	// 반대쪽(Employee) 객체에도 참조를 설정하는 메소드
	public void setEmployee(Employee employee) {
		this.employee = employee;
		employee.setCard(this);
	}
	
//	@ManyToOne
//	@JoinColumn(name = "EMP_CARD_ID")
//	private List<Employee> employeeList;
	
}
