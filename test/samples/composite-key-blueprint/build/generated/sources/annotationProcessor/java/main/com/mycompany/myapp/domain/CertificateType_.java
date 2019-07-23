package com.mycompany.myapp.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CertificateType.class)
public abstract class CertificateType_ {

	public static volatile SingularAttribute<CertificateType, String> name;
	public static volatile SingularAttribute<CertificateType, Long> id;
	public static volatile SetAttribute<CertificateType, EmployeeSkillCertificate> employeeSkillCertificates;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String EMPLOYEE_SKILL_CERTIFICATES = "employeeSkillCertificates";

}

