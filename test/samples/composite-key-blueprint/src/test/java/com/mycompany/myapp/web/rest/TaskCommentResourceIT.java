package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.CompositekeyApp;
import com.mycompany.myapp.domain.TaskComment;
import com.mycompany.myapp.domain.Task;
import com.mycompany.myapp.repository.TaskCommentRepository;
import com.mycompany.myapp.service.TaskCommentService;
import com.mycompany.myapp.service.dto.TaskCommentDTO;
import com.mycompany.myapp.service.mapper.TaskCommentMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.TaskCommentCriteria;
import com.mycompany.myapp.service.TaskCommentQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link TaskCommentResource} REST controller.
 */
@SpringBootTest(classes = CompositekeyApp.class)
public class TaskCommentResourceIT {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private TaskCommentRepository taskCommentRepository;

    @Autowired
    private TaskCommentMapper taskCommentMapper;

    @Autowired
    private TaskCommentService taskCommentService;

    @Autowired
    private TaskCommentQueryService taskCommentQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTaskCommentMockMvc;

    private TaskComment taskComment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaskCommentResource taskCommentResource = new TaskCommentResource(taskCommentService, taskCommentQueryService);
        this.restTaskCommentMockMvc = MockMvcBuilders.standaloneSetup(taskCommentResource)
            .setRemoveSemicolonContent(false)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskComment createEntity(EntityManager em) {
        TaskComment taskComment = new TaskComment()
            .value(DEFAULT_VALUE);
        // Add required entity
        Task newTask = TaskResourceIT.createEntity(em);
        Task task = TestUtil.findAll(em, Task.class).stream()
            .filter(x -> x.getName().equals(newTask.getName()))
            .findAny().orElse(null);
        if (task == null) {
            task = newTask;
            em.persist(task);
            em.flush();
        }
        taskComment.setTask(task);
        return taskComment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskComment createUpdatedEntity(EntityManager em) {
        TaskComment taskComment = new TaskComment()
            .value(UPDATED_VALUE);
        // Add required entity
        Task newTask = TaskResourceIT.createUpdatedEntity(em);
        Task task = TestUtil.findAll(em, Task.class).stream()
            .filter(x -> x.getName().equals(newTask.getName()))
            .findAny().orElse(null);
        if (task == null) {
            task = newTask;
            em.persist(task);
            em.flush();
        }
        taskComment.setTask(task);
        return taskComment;
    }

    @BeforeEach
    public void initTest() {
        taskComment = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaskComment() throws Exception {
        int databaseSizeBeforeCreate = taskCommentRepository.findAll().size();

        // Create the TaskComment
        TaskCommentDTO taskCommentDTO = taskCommentMapper.toDto(taskComment);
        restTaskCommentMockMvc.perform(post("/api/task-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskCommentDTO)))
            .andExpect(status().isCreated());

        // Validate the TaskComment in the database
        List<TaskComment> taskCommentList = taskCommentRepository.findAll();
        assertThat(taskCommentList).hasSize(databaseSizeBeforeCreate + 1);
        TaskComment testTaskComment = taskCommentList.get(taskCommentList.size() - 1);
        assertThat(testTaskComment.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createTaskCommentWithExistingId() throws Exception {
        taskCommentRepository.save(taskComment);
        int databaseSizeBeforeCreate = taskCommentRepository.findAll().size();

        // Create the TaskComment with an existing ID
        taskComment.setId(taskComment.getId());
        TaskCommentDTO taskCommentDTO = taskCommentMapper.toDto(taskComment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskCommentMockMvc.perform(post("/api/task-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskCommentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaskComment in the database
        List<TaskComment> taskCommentList = taskCommentRepository.findAll();
        assertThat(taskCommentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = taskCommentRepository.findAll().size();
        // set the field null
        taskComment.setValue(null);

        // Create the TaskComment, which fails.
        TaskCommentDTO taskCommentDTO = taskCommentMapper.toDto(taskComment);

        restTaskCommentMockMvc.perform(post("/api/task-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskCommentDTO)))
            .andExpect(status().isBadRequest());

        List<TaskComment> taskCommentList = taskCommentRepository.findAll();
        assertThat(taskCommentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTaskComments() throws Exception {
        // Initialize the database
        taskCommentRepository.saveAndFlush(taskComment);

        // Get all the taskCommentList
        restTaskCommentMockMvc.perform(get("/api/task-comments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskComment.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }

    @Test
    @Transactional
    public void getTaskComment() throws Exception {
        // Initialize the database
        taskCommentRepository.saveAndFlush(taskComment);

        // Get the taskComment
        restTaskCommentMockMvc.perform(get("/api/task-comments/{id}", taskComment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(taskComment.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getAllTaskCommentsByValueIsEqualToSomething() throws Exception {
        // Initialize the database
        taskCommentRepository.saveAndFlush(taskComment);

        // Get all the taskCommentList where value equals to DEFAULT_VALUE
        defaultTaskCommentShouldBeFound("value.equals=" + DEFAULT_VALUE);

        // Get all the taskCommentList where value equals to UPDATED_VALUE
        defaultTaskCommentShouldNotBeFound("value.equals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllTaskCommentsByValueIsInShouldWork() throws Exception {
        // Initialize the database
        taskCommentRepository.saveAndFlush(taskComment);

        // Get all the taskCommentList where value in DEFAULT_VALUE or UPDATED_VALUE
        defaultTaskCommentShouldBeFound("value.in=" + DEFAULT_VALUE + "," + UPDATED_VALUE);

        // Get all the taskCommentList where value equals to UPDATED_VALUE
        defaultTaskCommentShouldNotBeFound("value.in=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllTaskCommentsByValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        taskCommentRepository.saveAndFlush(taskComment);

        // Get all the taskCommentList where value is not null
        defaultTaskCommentShouldBeFound("value.specified=true");

        // Get all the taskCommentList where value is null
        defaultTaskCommentShouldNotBeFound("value.specified=false");
    }

    @Test
    @Transactional
    public void getAllTaskCommentsByTaskIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        Task task = taskComment.getTask();
        taskCommentRepository.saveAndFlush(taskComment);
        Long taskId = task.getId();

        // Get all the taskCommentList where taskId equals to taskId
        defaultTaskCommentShouldBeFound("taskId.equals=" + taskId);

        // Get all the taskCommentList where taskId equals to taskId + 1
        defaultTaskCommentShouldNotBeFound("taskId.equals=" + (taskId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTaskCommentShouldBeFound(String filter) throws Exception {
        restTaskCommentMockMvc.perform(get("/api/task-comments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskComment.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));

        // Check, that the count call also returns 1
        restTaskCommentMockMvc.perform(get("/api/task-comments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTaskCommentShouldNotBeFound(String filter) throws Exception {
        restTaskCommentMockMvc.perform(get("/api/task-comments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTaskCommentMockMvc.perform(get("/api/task-comments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTaskComment() throws Exception {
        // Get the taskComment
        restTaskCommentMockMvc.perform(get("/api/task-comments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaskComment() throws Exception {
        // Initialize the database
        taskCommentRepository.saveAndFlush(taskComment);

        int databaseSizeBeforeUpdate = taskCommentRepository.findAll().size();

        // Update the taskComment
        TaskComment updatedTaskComment = taskCommentRepository.findById(taskComment.getId()).get();
        // Disconnect from session so that the updates on updatedTaskComment are not directly saved in db
        em.detach(updatedTaskComment);
        updatedTaskComment
            .value(UPDATED_VALUE);
        TaskCommentDTO taskCommentDTO = taskCommentMapper.toDto(updatedTaskComment);

        restTaskCommentMockMvc.perform(put("/api/task-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskCommentDTO)))
            .andExpect(status().isOk());

        // Validate the TaskComment in the database
        List<TaskComment> taskCommentList = taskCommentRepository.findAll();
        assertThat(taskCommentList).hasSize(databaseSizeBeforeUpdate);
        TaskComment testTaskComment = taskCommentList.get(taskCommentList.size() - 1);
        assertThat(testTaskComment.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingTaskComment() throws Exception {
        int databaseSizeBeforeUpdate = taskCommentRepository.findAll().size();

        // Create the TaskComment
        TaskCommentDTO taskCommentDTO = taskCommentMapper.toDto(taskComment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskCommentMockMvc.perform(put("/api/task-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskCommentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaskComment in the database
        List<TaskComment> taskCommentList = taskCommentRepository.findAll();
        assertThat(taskCommentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTaskComment() throws Exception {
        // Initialize the database
        taskCommentRepository.saveAndFlush(taskComment);

        int databaseSizeBeforeDelete = taskCommentRepository.findAll().size();

        // Delete the taskComment
        restTaskCommentMockMvc.perform(delete("/api/task-comments/{id}", taskComment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaskComment> taskCommentList = taskCommentRepository.findAll();
        assertThat(taskCommentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskComment.class);
        TaskComment taskComment1 = new TaskComment();
        taskComment1.setId(1L);
        TaskComment taskComment2 = new TaskComment();
        taskComment2.setId(taskComment1.getId());
        assertThat(taskComment1).isEqualTo(taskComment2);
        taskComment2.setId(2L);
        assertThat(taskComment1).isNotEqualTo(taskComment2);
        taskComment1.setId(null);
        assertThat(taskComment1).isNotEqualTo(taskComment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskCommentDTO.class);
        TaskCommentDTO taskCommentDTO1 = new TaskCommentDTO();
        taskCommentDTO1.setId(1L);
        TaskCommentDTO taskCommentDTO2 = new TaskCommentDTO();
        assertThat(taskCommentDTO1).isNotEqualTo(taskCommentDTO2);
        taskCommentDTO2.setId(taskCommentDTO1.getId());
        assertThat(taskCommentDTO1).isEqualTo(taskCommentDTO2);
        taskCommentDTO2.setId(2L);
        assertThat(taskCommentDTO1).isNotEqualTo(taskCommentDTO2);
        taskCommentDTO1.setId(null);
        assertThat(taskCommentDTO1).isNotEqualTo(taskCommentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(taskCommentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(taskCommentMapper.fromId(null)).isNull();
    }
}
