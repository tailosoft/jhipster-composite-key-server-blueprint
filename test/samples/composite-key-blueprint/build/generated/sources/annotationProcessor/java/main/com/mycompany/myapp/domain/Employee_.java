package com.mycompany.myapp.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SetAttribute<Employee, EmployeeSkill> skills;
	public static volatile SingularAttribute<Employee, String> fullname;
	public static volatile SingularAttribute<Employee, String> username;

	public static final String SKILLS = "skills";
	public static final String FULLNAME = "fullname";
	public static final String USERNAME = "username";

}

