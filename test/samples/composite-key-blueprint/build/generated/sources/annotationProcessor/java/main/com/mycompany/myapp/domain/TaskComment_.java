package com.mycompany.myapp.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TaskComment.class)
public abstract class TaskComment_ {

	public static volatile SingularAttribute<TaskComment, Task> task;
	public static volatile SingularAttribute<TaskComment, Long> id;
	public static volatile SingularAttribute<TaskComment, String> value;

	public static final String TASK = "task";
	public static final String ID = "id";
	public static final String VALUE = "value";

}

