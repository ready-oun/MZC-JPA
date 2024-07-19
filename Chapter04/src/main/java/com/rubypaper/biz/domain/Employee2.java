package com.rubypaper.biz.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/*
 * 영속성 전이 테스트용 엔터티
 */

@Data
@Entity
@Table(name = "S_EMP")
public class Employee2 {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 25, nullable = false)
	private String name;

	//다:1 관계( 다:사원, 1:부서, 여러명의 사원이 한 부서에 속함)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="DEPT_ID")//S_EMP table 생성시 FK 설정
	private Department2 dept;
	// FK 라는 연관관계를 가지고 있는 소유자 => Employee
	
	/*
	 * null 을 이용한 관계 끊기
	 * 
	 * 멤버변수 dept 를 null 로 초기화.
	 * => 현재 엔터티의 사원은 어떤 부서에도 소속되는게 아닌 상태가 됨.
	 */
	public void setDept(Department2 dept) {
		this.dept = dept;
		
		// 매개변수가 null 이면, 아래의 코드에서 nullPointerException 이 발생.
		if(dept != null) {
			// 부서 정보가 할당되었다는 것은 부서 배치가 끝났음을 의미.
			// 따라서, 이 부서에는 현재 엔터티의 사원이 소속된다는 의미와 동일.
			dept.getEmpList().add(this);
		}
		
	}
	
	public void standby() {
		this.dept = null;
	}
	
}