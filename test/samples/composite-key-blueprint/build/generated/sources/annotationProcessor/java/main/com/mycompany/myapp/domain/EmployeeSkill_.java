package com.mycompany.myapp.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmployeeSkill.class)
public abstract class EmployeeSkill_ {

	public static volatile SingularAttribute<EmployeeSkill, Integer> level;
	public static volatile SingularAttribute<EmployeeSkill, String> name;
	public static volatile SingularAttribute<EmployeeSkill, EmployeeSkillId> id;
	public static volatile SingularAttribute<EmployeeSkill, Employee> employee;
	public static volatile SetAttribute<EmployeeSkill, EmployeeSkillCertificate> employeeSkillCertificates;
	public static volatile SetAttribute<EmployeeSkill, Task> tasks;

	public static final String LEVEL = "level";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String EMPLOYEE = "employee";
	public static final String EMPLOYEE_SKILL_CERTIFICATES = "employeeSkillCertificates";
	public static final String TASKS = "tasks";

}

