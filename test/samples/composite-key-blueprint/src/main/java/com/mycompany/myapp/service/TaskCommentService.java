package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TaskCommentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.TaskComment}.
 */
public interface TaskCommentService {

    /**
     * Save a taskComment.
     *
     * @param taskCommentDTO the entity to save.
     * @return the persisted entity.
     */
    TaskCommentDTO save(TaskCommentDTO taskCommentDTO);

    /**
     * Get all the taskComments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaskCommentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" taskComment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaskCommentDTO> findOne(Long id);

    /**
     * Delete the "id" taskComment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
