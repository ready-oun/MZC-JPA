package com.rubypaper.biz.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 식별자 클래스
 * 
 * 복합키 : 식별자가 될 수 있는 복합키여야만 함
 * 			따라서, 식별자 클래스의 복합키에 대한 후보군
 * 
		 * id, mailId = > 교재 기준
		 * id, name
		 * id, mailId, name
 * 
 *  식별자 클래스 작성 규칙 p.140
 *  1. 클래스에 @Embeddable  어노테이션 선언
 *  2. 반드시 java.io.Serializable 인터페이스 구현
 *  3. 기본 생성자와 모든 멤버 초기화하는 생성자 선언 @NoArgsConstructor @AllArgsConstructor
 *  4. 복합 키에 해당하는 변수들만 선언
 *  5. 값을 임의로 변경할 수 없도록 Getter 메소드만 제공  @Getter 
 *  6. equals와 hashCode 메소드를 재정의(Overriding)함  @EqualsAndHashCode
 */

@Embeddable // 식별자 클래스로 사용되는 클래스임을 나타냄
@NoArgsConstructor // lombok, 기본 생성자
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Employee12Id implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	private Long id;
	private String mailId;
}
