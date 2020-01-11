package com.mycompany.myapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.mycompany.myapp.domain.TaskComment;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.TaskCommentRepository;
import com.mycompany.myapp.service.dto.TaskCommentCriteria;
import com.mycompany.myapp.service.dto.TaskCommentDTO;
import com.mycompany.myapp.service.mapper.TaskCommentMapper;

/**
 * Service for executing complex queries for {@link TaskComment} entities in the database.
 * The main input is a {@link TaskCommentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TaskCommentDTO} or a {@link Page} of {@link TaskCommentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TaskCommentQueryService extends QueryService<TaskComment> {

    private final Logger log = LoggerFactory.getLogger(TaskCommentQueryService.class);

    private final TaskCommentRepository taskCommentRepository;

    private final TaskCommentMapper taskCommentMapper;

    public TaskCommentQueryService(TaskCommentRepository taskCommentRepository, TaskCommentMapper taskCommentMapper) {
        this.taskCommentRepository = taskCommentRepository;
        this.taskCommentMapper = taskCommentMapper;
    }

    /**
     * Return a {@link List} of {@link TaskCommentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TaskCommentDTO> findByCriteria(TaskCommentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TaskComment> specification = createSpecification(criteria);
        return taskCommentMapper.toDto(taskCommentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TaskCommentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TaskCommentDTO> findByCriteria(TaskCommentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TaskComment> specification = createSpecification(criteria);
        return taskCommentRepository.findAll(specification, page)
            .map(taskCommentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TaskCommentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TaskComment> specification = createSpecification(criteria);
        return taskCommentRepository.count(specification);
    }

    /**
     * Function to convert {@link TaskCommentCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TaskComment> createSpecification(TaskCommentCriteria criteria) {
        Specification<TaskComment> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TaskComment_.id));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValue(), TaskComment_.value));
            }
            if (criteria.getTaskId() != null) {
                specification = specification.and(buildSpecification(criteria.getTaskId(),
                    root -> root.join(TaskComment_.task, JoinType.LEFT).get(Task_.id)));
            }
        }
        return specification;
    }
}
