package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TaskComment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TaskComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskCommentRepository extends JpaRepository<TaskComment, Long>, JpaSpecificationExecutor<TaskComment> {
}
