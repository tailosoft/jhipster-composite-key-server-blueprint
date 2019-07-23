package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TaskCommentDTO;

import java.util.List;
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
     * @return the list of entities.
     */
    List<TaskCommentDTO> findAll();


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
