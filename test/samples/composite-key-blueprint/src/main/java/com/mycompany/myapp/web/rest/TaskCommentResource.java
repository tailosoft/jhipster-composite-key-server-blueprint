package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.TaskCommentService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.TaskCommentDTO;
import com.mycompany.myapp.service.dto.TaskCommentCriteria;
import com.mycompany.myapp.service.TaskCommentQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.TaskComment}.
 */
@RestController
@RequestMapping("/api")
public class TaskCommentResource {

    private final Logger log = LoggerFactory.getLogger(TaskCommentResource.class);

    private static final String ENTITY_NAME = "taskComment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaskCommentService taskCommentService;

    private final TaskCommentQueryService taskCommentQueryService;

    public TaskCommentResource(TaskCommentService taskCommentService, TaskCommentQueryService taskCommentQueryService) {
        this.taskCommentService = taskCommentService;
        this.taskCommentQueryService = taskCommentQueryService;
    }

    /**
     * {@code POST  /task-comments} : Create a new taskComment.
     *
     * @param taskCommentDTO the taskCommentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskCommentDTO, or with status {@code 400 (Bad Request)} if the taskComment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/task-comments")
    public ResponseEntity<TaskCommentDTO> createTaskComment(@Valid @RequestBody TaskCommentDTO taskCommentDTO) throws URISyntaxException {
        log.debug("REST request to save TaskComment : {}", taskCommentDTO);
        if (taskCommentDTO.getId() != null) {
            throw new BadRequestAlertException("A new taskComment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskCommentDTO result = taskCommentService.save(taskCommentDTO);
        return ResponseEntity.created(new URI("/api/task-comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /task-comments} : Updates an existing taskComment.
     *
     * @param taskCommentDTO the taskCommentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskCommentDTO,
     * or with status {@code 400 (Bad Request)} if the taskCommentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taskCommentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/task-comments")
    public ResponseEntity<TaskCommentDTO> updateTaskComment(@Valid @RequestBody TaskCommentDTO taskCommentDTO) throws URISyntaxException {
        log.debug("REST request to update TaskComment : {}", taskCommentDTO);
        if (taskCommentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TaskCommentDTO result = taskCommentService.save(taskCommentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /task-comments} : get all the taskComments.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taskComments in body.
     */
    @GetMapping("/task-comments")
    public ResponseEntity<List<TaskCommentDTO>> getAllTaskComments(TaskCommentCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TaskComments by criteria: {}", criteria);
        Page<TaskCommentDTO> page = taskCommentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /task-comments/count} : count all the taskComments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/task-comments/count")
    public ResponseEntity<Long> countTaskComments(TaskCommentCriteria criteria) {
        log.debug("REST request to count TaskComments by criteria: {}", criteria);
        return ResponseEntity.ok().body(taskCommentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /task-comments/:id} : get the "id" taskComment.
     *
     * @param id the id of the taskCommentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskCommentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/task-comments/{id}")
    public ResponseEntity<TaskCommentDTO> getTaskComment(@PathVariable Long id) {
        log.debug("REST request to get TaskComment : {}", id);
        Optional<TaskCommentDTO> taskCommentDTO = taskCommentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskCommentDTO);
    }

    /**
     * {@code DELETE  /task-comments/:id} : delete the "id" taskComment.
     *
     * @param id the id of the taskCommentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/task-comments/{id}")
    public ResponseEntity<Void> deleteTaskComment(@PathVariable Long id) {
        log.debug("REST request to delete TaskComment : {}", id);
        taskCommentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
