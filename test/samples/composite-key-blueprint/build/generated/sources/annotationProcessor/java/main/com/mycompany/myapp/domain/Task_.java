package com.mycompany.myapp.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Task.class)
public abstract class Task_ {

	public static volatile SetAttribute<Task, EmployeeSkill> employeeSkills;
	public static volatile SingularAttribute<Task, String> name;
	public static volatile SingularAttribute<Task, Long> id;
	public static volatile SetAttribute<Task, TaskComment> tasks;

	public static final String EMPLOYEE_SKILLS = "employeeSkills";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String TASKS = "tasks";

}

