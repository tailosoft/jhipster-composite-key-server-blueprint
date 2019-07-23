package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EmployeeSkill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.mycompany.myapp.domain.EmployeeSkillId;

/**
 * Spring Data  repository for the EmployeeSkill entity.
 */
@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, EmployeeSkillId>, JpaSpecificationExecutor<EmployeeSkill> {

    @Query(value = "select distinct employeeSkill from EmployeeSkill employeeSkill left join fetch employeeSkill.tasks",
        countQuery = "select count(distinct employeeSkill) from EmployeeSkill employeeSkill")
    Page<EmployeeSkill> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct employeeSkill from EmployeeSkill employeeSkill left join fetch employeeSkill.tasks")
    List<EmployeeSkill> findAllWithEagerRelationships();

    @Query("select employeeSkill from EmployeeSkill employeeSkill left join fetch employeeSkill.tasks where employeeSkill.id =:id")
    Optional<EmployeeSkill> findOneWithEagerRelationships(@Param("id") EmployeeSkillId id);

}
