-- 테이블 삭제
drop table s_dept;
drop table s_emp;

-- 부서 테이블
create table s_dept(
	dept_id NUMBER(7) CONSTRAINT s_dept_id_nn NOT NULL, -- 부서 아이디
	name VARCHAR2(25) CONSTRAINT s_dept_name_nn NOT NULL, -- 부서 이름
    CONSTRAINT s_dept_id_pk PRIMARY KEY (dept_id) 
);
    
-- 직원 테이블
create table s_emp(
	id NUMBER(7) CONSTRAINT s_emp_id_nn NOT NULL, -- 직원 아이디
	name VARCHAR2(25) CONSTRAINT s_dept_name_nn NOT NULL, 
	dept_id NUMBER(7),
	CONSTRAINT s_emp_id_pk PRIMARY KEY (id)    
);

alter table s_emp ADD CONSTRAINT s_emp_dept_id_fk
	FOREIGN KEY (dept_id) REFERENCES s_dept (dept_id);
    
insert into s_dept values(1, '개발부');
insert into s_dept values(1, '둘리', 1);
insert into s_dept values(1, '도우너', 2);
