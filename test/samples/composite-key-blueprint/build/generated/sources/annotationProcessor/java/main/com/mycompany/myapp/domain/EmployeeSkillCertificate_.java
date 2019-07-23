package com.mycompany.myapp.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmployeeSkillCertificate.class)
public abstract class EmployeeSkillCertificate_ {

	public static volatile SingularAttribute<EmployeeSkillCertificate, LocalDate> date;
	public static volatile SingularAttribute<EmployeeSkillCertificate, Integer> grade;
	public static volatile SingularAttribute<EmployeeSkillCertificate, EmployeeSkill> skill;
	public static volatile SingularAttribute<EmployeeSkillCertificate, EmployeeSkillCertificateId> id;
	public static volatile SingularAttribute<EmployeeSkillCertificate, CertificateType> type;

	public static final String DATE = "date";
	public static final String GRADE = "grade";
	public static final String SKILL = "skill";
	public static final String ID = "id";
	public static final String TYPE = "type";

}

