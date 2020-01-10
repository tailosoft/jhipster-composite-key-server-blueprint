package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.CompositekeyApp;
import com.mycompany.myapp.domain.Task;
import com.mycompany.myapp.domain.TaskComment;
import com.mycompany.myapp.domain.EmployeeSkill;
import com.mycompany.myapp.repository.TaskRepository;
import com.mycompany.myapp.service.TaskService;
import com.mycompany.myapp.service.dto.TaskDTO;
import com.mycompany.myapp.service.mapper.TaskMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.TaskCriteria;
import com.mycompany.myapp.service.TaskQueryService;

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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.TaskType;
/**
 * Integration tests for the {@Link TaskResource} REST controller.
 */
@SpringBootTest(classes = CompositekeyApp.class)
public class TaskResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final TaskType DEFAULT_TYPE = TaskType.TYPE1;
    private static final TaskType UPDATED_TYPE = TaskType.TYPE2;

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_END_DATE = LocalDate.ofEpochDay(-1L);

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final Instant DEFAULT_MODIFIED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_MODIFIED_AT = Instant.ofEpochMilli(-1L);

    private static final Boolean DEFAULT_DONE = false;
    private static final Boolean UPDATED_DONE = true;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_ATTACHMENT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ATTACHMENT = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ATTACHMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ATTACHMENT_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_PICTURE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PICTURE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PICTURE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PICTURE_CONTENT_TYPE = "image/png";

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskQueryService taskQueryService;

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

    private MockMvc restTaskMockMvc;

    private Task task;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaskResource taskResource = new TaskResource(taskService, taskQueryService);
        this.restTaskMockMvc = MockMvcBuilders.standaloneSetup(taskResource)
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
    public static Task createEntity(EntityManager em) {
        Task task = new Task()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .endDate(DEFAULT_END_DATE)
            .createdAt(DEFAULT_CREATED_AT)
            .modifiedAt(DEFAULT_MODIFIED_AT)
            .done(DEFAULT_DONE)
            .description(DEFAULT_DESCRIPTION)
            .attachment(DEFAULT_ATTACHMENT)
            .attachmentContentType(DEFAULT_ATTACHMENT_CONTENT_TYPE)
            .picture(DEFAULT_PICTURE)
            .pictureContentType(DEFAULT_PICTURE_CONTENT_TYPE);
        return task;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Task createUpdatedEntity(EntityManager em) {
        Task task = new Task()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .endDate(UPDATED_END_DATE)
            .createdAt(UPDATED_CREATED_AT)
            .modifiedAt(UPDATED_MODIFIED_AT)
            .done(UPDATED_DONE)
            .description(UPDATED_DESCRIPTION)
            .attachment(UPDATED_ATTACHMENT)
            .attachmentContentType(UPDATED_ATTACHMENT_CONTENT_TYPE)
            .picture(UPDATED_PICTURE)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE);
        return task;
    }

    @BeforeEach
    public void initTest() {
        task = createEntity(em);
    }

    @Test
    @Transactional
    public void createTask() throws Exception {
        int databaseSizeBeforeCreate = taskRepository.findAll().size();

        // Create the Task
        TaskDTO taskDTO = taskMapper.toDto(task);
        restTaskMockMvc.perform(post("/api/tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isCreated());

        // Validate the Task in the database
        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeCreate + 1);
        Task testTask = taskList.get(taskList.size() - 1);
        assertThat(testTask.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTask.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTask.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testTask.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testTask.getModifiedAt()).isEqualTo(DEFAULT_MODIFIED_AT);
        assertThat(testTask.isDone()).isEqualTo(DEFAULT_DONE);
        assertThat(testTask.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTask.getAttachment()).isEqualTo(DEFAULT_ATTACHMENT);
        assertThat(testTask.getAttachmentContentType()).isEqualTo(DEFAULT_ATTACHMENT_CONTENT_TYPE);
        assertThat(testTask.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testTask.getPictureContentType()).isEqualTo(DEFAULT_PICTURE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createTaskWithExistingId() throws Exception {
        taskRepository.save(task);
        int databaseSizeBeforeCreate = taskRepository.findAll().size();

        // Create the Task with an existing ID
        task.setId(task.getId());
        TaskDTO taskDTO = taskMapper.toDto(task);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskMockMvc.perform(post("/api/tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Task in the database
        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = taskRepository.findAll().size();
        // set the field null
        task.setName(null);

        // Create the Task, which fails.
        TaskDTO taskDTO = taskMapper.toDto(task);

        restTaskMockMvc.perform(post("/api/tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isBadRequest());

        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = taskRepository.findAll().size();
        // set the field null
        task.setType(null);

        // Create the Task, which fails.
        TaskDTO taskDTO = taskMapper.toDto(task);

        restTaskMockMvc.perform(post("/api/tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isBadRequest());

        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = taskRepository.findAll().size();
        // set the field null
        task.setCreatedAt(null);

        // Create the Task, which fails.
        TaskDTO taskDTO = taskMapper.toDto(task);

        restTaskMockMvc.perform(post("/api/tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isBadRequest());

        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifiedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = taskRepository.findAll().size();
        // set the field null
        task.setModifiedAt(null);

        // Create the Task, which fails.
        TaskDTO taskDTO = taskMapper.toDto(task);

        restTaskMockMvc.perform(post("/api/tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isBadRequest());

        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = taskRepository.findAll().size();
        // set the field null
        task.setDone(null);

        // Create the Task, which fails.
        TaskDTO taskDTO = taskMapper.toDto(task);

        restTaskMockMvc.perform(post("/api/tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isBadRequest());

        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTasks() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList
        restTaskMockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(task.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].modifiedAt").value(hasItem(DEFAULT_MODIFIED_AT.toString())))
            .andExpect(jsonPath("$.[*].done").value(hasItem(DEFAULT_DONE.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].attachmentContentType").value(hasItem(DEFAULT_ATTACHMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].attachment").value(hasItem(Base64Utils.encodeToString(DEFAULT_ATTACHMENT))))
            .andExpect(jsonPath("$.[*].pictureContentType").value(hasItem(DEFAULT_PICTURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(Base64Utils.encodeToString(DEFAULT_PICTURE))));
    }

    @Test
    @Transactional
    public void getTask() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get the task
        restTaskMockMvc.perform(get("/api/tasks/{id}", task.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(task.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.modifiedAt").value(DEFAULT_MODIFIED_AT.toString()))
            .andExpect(jsonPath("$.done").value(DEFAULT_DONE.booleanValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.attachmentContentType").value(DEFAULT_ATTACHMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.attachment").value(Base64Utils.encodeToString(DEFAULT_ATTACHMENT)))
            .andExpect(jsonPath("$.pictureContentType").value(DEFAULT_PICTURE_CONTENT_TYPE))
            .andExpect(jsonPath("$.picture").value(Base64Utils.encodeToString(DEFAULT_PICTURE)));
    }

    @Test
    @Transactional
    public void getAllTasksByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where name equals to DEFAULT_NAME
        defaultTaskShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the taskList where name equals to UPDATED_NAME
        defaultTaskShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllTasksByNameIsInShouldWork() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where name in DEFAULT_NAME or UPDATED_NAME
        defaultTaskShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the taskList where name equals to UPDATED_NAME
        defaultTaskShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllTasksByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where name is not null
        defaultTaskShouldBeFound("name.specified=true");

        // Get all the taskList where name is null
        defaultTaskShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllTasksByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where type equals to DEFAULT_TYPE
        defaultTaskShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the taskList where type equals to UPDATED_TYPE
        defaultTaskShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllTasksByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultTaskShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the taskList where type equals to UPDATED_TYPE
        defaultTaskShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllTasksByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where type is not null
        defaultTaskShouldBeFound("type.specified=true");

        // Get all the taskList where type is null
        defaultTaskShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    public void getAllTasksByEndDateIsEqualToSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where endDate equals to DEFAULT_END_DATE
        defaultTaskShouldBeFound("endDate.equals=" + DEFAULT_END_DATE);

        // Get all the taskList where endDate equals to UPDATED_END_DATE
        defaultTaskShouldNotBeFound("endDate.equals=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllTasksByEndDateIsInShouldWork() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where endDate in DEFAULT_END_DATE or UPDATED_END_DATE
        defaultTaskShouldBeFound("endDate.in=" + DEFAULT_END_DATE + "," + UPDATED_END_DATE);

        // Get all the taskList where endDate equals to UPDATED_END_DATE
        defaultTaskShouldNotBeFound("endDate.in=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllTasksByEndDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where endDate is not null
        defaultTaskShouldBeFound("endDate.specified=true");

        // Get all the taskList where endDate is null
        defaultTaskShouldNotBeFound("endDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllTasksByEndDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where endDate is greater than or equal to DEFAULT_END_DATE
        defaultTaskShouldBeFound("endDate.greaterThanOrEqual=" + DEFAULT_END_DATE);

        // Get all the taskList where endDate is greater than or equal to UPDATED_END_DATE
        defaultTaskShouldNotBeFound("endDate.greaterThanOrEqual=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllTasksByEndDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where endDate is less than or equal to DEFAULT_END_DATE
        defaultTaskShouldBeFound("endDate.lessThanOrEqual=" + DEFAULT_END_DATE);

        // Get all the taskList where endDate is less than or equal to SMALLER_END_DATE
        defaultTaskShouldNotBeFound("endDate.lessThanOrEqual=" + SMALLER_END_DATE);
    }

    @Test
    @Transactional
    public void getAllTasksByEndDateIsLessThanSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where endDate is less than DEFAULT_END_DATE
        defaultTaskShouldNotBeFound("endDate.lessThan=" + DEFAULT_END_DATE);

        // Get all the taskList where endDate is less than UPDATED_END_DATE
        defaultTaskShouldBeFound("endDate.lessThan=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllTasksByEndDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where endDate is greater than DEFAULT_END_DATE
        defaultTaskShouldNotBeFound("endDate.greaterThan=" + DEFAULT_END_DATE);

        // Get all the taskList where endDate is greater than SMALLER_END_DATE
        defaultTaskShouldBeFound("endDate.greaterThan=" + SMALLER_END_DATE);
    }


    @Test
    @Transactional
    public void getAllTasksByCreatedAtIsEqualToSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where createdAt equals to DEFAULT_CREATED_AT
        defaultTaskShouldBeFound("createdAt.equals=" + DEFAULT_CREATED_AT);

        // Get all the taskList where createdAt equals to UPDATED_CREATED_AT
        defaultTaskShouldNotBeFound("createdAt.equals=" + UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void getAllTasksByCreatedAtIsInShouldWork() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where createdAt in DEFAULT_CREATED_AT or UPDATED_CREATED_AT
        defaultTaskShouldBeFound("createdAt.in=" + DEFAULT_CREATED_AT + "," + UPDATED_CREATED_AT);

        // Get all the taskList where createdAt equals to UPDATED_CREATED_AT
        defaultTaskShouldNotBeFound("createdAt.in=" + UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void getAllTasksByCreatedAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where createdAt is not null
        defaultTaskShouldBeFound("createdAt.specified=true");

        // Get all the taskList where createdAt is null
        defaultTaskShouldNotBeFound("createdAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllTasksByCreatedAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where createdAt is greater than or equal to DEFAULT_CREATED_AT
        defaultTaskShouldBeFound("createdAt.greaterThanOrEqual=" + DEFAULT_CREATED_AT);

        // Get all the taskList where createdAt is greater than or equal to UPDATED_CREATED_AT
        defaultTaskShouldNotBeFound("createdAt.greaterThanOrEqual=" + UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void getAllTasksByCreatedAtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where createdAt is less than or equal to DEFAULT_CREATED_AT
        defaultTaskShouldBeFound("createdAt.lessThanOrEqual=" + DEFAULT_CREATED_AT);

        // Get all the taskList where createdAt is less than or equal to SMALLER_CREATED_AT
        defaultTaskShouldNotBeFound("createdAt.lessThanOrEqual=" + SMALLER_CREATED_AT);
    }

    @Test
    @Transactional
    public void getAllTasksByCreatedAtIsLessThanSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where createdAt is less than DEFAULT_CREATED_AT
        defaultTaskShouldNotBeFound("createdAt.lessThan=" + DEFAULT_CREATED_AT);

        // Get all the taskList where createdAt is less than UPDATED_CREATED_AT
        defaultTaskShouldBeFound("createdAt.lessThan=" + UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void getAllTasksByCreatedAtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where createdAt is greater than DEFAULT_CREATED_AT
        defaultTaskShouldNotBeFound("createdAt.greaterThan=" + DEFAULT_CREATED_AT);

        // Get all the taskList where createdAt is greater than SMALLER_CREATED_AT
        defaultTaskShouldBeFound("createdAt.greaterThan=" + SMALLER_CREATED_AT);
    }


    @Test
    @Transactional
    public void getAllTasksByModifiedAtIsEqualToSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where modifiedAt equals to DEFAULT_MODIFIED_AT
        defaultTaskShouldBeFound("modifiedAt.equals=" + DEFAULT_MODIFIED_AT);

        // Get all the taskList where modifiedAt equals to UPDATED_MODIFIED_AT
        defaultTaskShouldNotBeFound("modifiedAt.equals=" + UPDATED_MODIFIED_AT);
    }

    @Test
    @Transactional
    public void getAllTasksByModifiedAtIsInShouldWork() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where modifiedAt in DEFAULT_MODIFIED_AT or UPDATED_MODIFIED_AT
        defaultTaskShouldBeFound("modifiedAt.in=" + DEFAULT_MODIFIED_AT + "," + UPDATED_MODIFIED_AT);

        // Get all the taskList where modifiedAt equals to UPDATED_MODIFIED_AT
        defaultTaskShouldNotBeFound("modifiedAt.in=" + UPDATED_MODIFIED_AT);
    }

    @Test
    @Transactional
    public void getAllTasksByModifiedAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where modifiedAt is not null
        defaultTaskShouldBeFound("modifiedAt.specified=true");

        // Get all the taskList where modifiedAt is null
        defaultTaskShouldNotBeFound("modifiedAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllTasksByDoneIsEqualToSomething() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where done equals to DEFAULT_DONE
        defaultTaskShouldBeFound("done.equals=" + DEFAULT_DONE);

        // Get all the taskList where done equals to UPDATED_DONE
        defaultTaskShouldNotBeFound("done.equals=" + UPDATED_DONE);
    }

    @Test
    @Transactional
    public void getAllTasksByDoneIsInShouldWork() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where done in DEFAULT_DONE or UPDATED_DONE
        defaultTaskShouldBeFound("done.in=" + DEFAULT_DONE + "," + UPDATED_DONE);

        // Get all the taskList where done equals to UPDATED_DONE
        defaultTaskShouldNotBeFound("done.in=" + UPDATED_DONE);
    }

    @Test
    @Transactional
    public void getAllTasksByDoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList where done is not null
        defaultTaskShouldBeFound("done.specified=true");

        // Get all the taskList where done is null
        defaultTaskShouldNotBeFound("done.specified=false");
    }

    @Test
    @Transactional
    public void getAllTasksByCommentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        TaskComment comment = TaskCommentResourceIT.createEntity(em);
        em.persist(comment);
        em.flush();
        task.addComment(comment);
        taskRepository.saveAndFlush(task);
        Long commentId = comment.getId();

        // Get all the taskList where commentId equals to commentId
        defaultTaskShouldBeFound("commentId.equals=" + commentId);

        // Get all the taskList where commentId equals to commentId + 1
        defaultTaskShouldNotBeFound("commentId.equals=" + (commentId + 1));
    }


    @Test
    @Transactional
    public void getAllTasksByEmployeeSkillNameIsEqualToSomething() throws Exception {
        // Initialize the database
        EmployeeSkill employeeSkill = EmployeeSkillResourceIT.createEntity(em);
        em.persist(employeeSkill);
        em.flush();
        task.addEmployeeSkill(employeeSkill);
        taskRepository.saveAndFlush(task);
        String employeeSkillName = employeeSkill.getId().getName();

        // Get all the taskList where employeeSkillName equals to employeeSkillName
        defaultTaskShouldBeFound("employeeSkillName.equals=" + employeeSkillName);

        // Get all the taskList where employeeSkillName equals to a different employeeSkillName
        defaultTaskShouldNotBeFound("employeeSkillName.equals=" + EmployeeSkillResourceIT.createUpdatedEntity(em).getId().getName());
    }


    @Test
    @Transactional
    public void getAllTasksByEmployeeSkillEmployeeUsernameIsEqualToSomething() throws Exception {
        // Initialize the database
        EmployeeSkill employeeSkill = EmployeeSkillResourceIT.createEntity(em);
        em.persist(employeeSkill);
        em.flush();
        task.addEmployeeSkill(employeeSkill);
        taskRepository.saveAndFlush(task);
        String employeeSkillEmployeeUsername = employeeSkill.getId().getEmployeeUsername();

        // Get all the taskList where employeeSkillEmployeeUsername equals to employeeSkillEmployeeUsername
        defaultTaskShouldBeFound("employeeSkillEmployeeUsername.equals=" + employeeSkillEmployeeUsername);

        // Get all the taskList where employeeSkillEmployeeUsername equals to a different employeeSkillEmployeeUsername
        defaultTaskShouldNotBeFound("employeeSkillEmployeeUsername.equals=" + EmployeeSkillResourceIT.createUpdatedEntity(em).getId().getEmployeeUsername());
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTaskShouldBeFound(String filter) throws Exception {
        restTaskMockMvc.perform(get("/api/tasks?" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(task.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].modifiedAt").value(hasItem(DEFAULT_MODIFIED_AT.toString())))
            .andExpect(jsonPath("$.[*].done").value(hasItem(DEFAULT_DONE.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].attachmentContentType").value(hasItem(DEFAULT_ATTACHMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].attachment").value(hasItem(Base64Utils.encodeToString(DEFAULT_ATTACHMENT))))
            .andExpect(jsonPath("$.[*].pictureContentType").value(hasItem(DEFAULT_PICTURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(Base64Utils.encodeToString(DEFAULT_PICTURE))));

        // Check, that the count call also returns 1
        restTaskMockMvc.perform(get("/api/tasks/count?" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTaskShouldNotBeFound(String filter) throws Exception {
        restTaskMockMvc.perform(get("/api/tasks?" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTaskMockMvc.perform(get("/api/tasks/count?" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTask() throws Exception {
        // Get the task
        restTaskMockMvc.perform(get("/api/tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTask() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        int databaseSizeBeforeUpdate = taskRepository.findAll().size();

        // Update the task
        Task updatedTask = taskRepository.findById(task.getId()).get();
        // Disconnect from session so that the updates on updatedTask are not directly saved in db
        em.detach(updatedTask);
        updatedTask
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .endDate(UPDATED_END_DATE)
            .createdAt(UPDATED_CREATED_AT)
            .modifiedAt(UPDATED_MODIFIED_AT)
            .done(UPDATED_DONE)
            .description(UPDATED_DESCRIPTION)
            .attachment(UPDATED_ATTACHMENT)
            .attachmentContentType(UPDATED_ATTACHMENT_CONTENT_TYPE)
            .picture(UPDATED_PICTURE)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE);
        TaskDTO taskDTO = taskMapper.toDto(updatedTask);

        restTaskMockMvc.perform(put("/api/tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isOk());

        // Validate the Task in the database
        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeUpdate);
        Task testTask = taskList.get(taskList.size() - 1);
        assertThat(testTask.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTask.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTask.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testTask.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testTask.getModifiedAt()).isEqualTo(UPDATED_MODIFIED_AT);
        assertThat(testTask.isDone()).isEqualTo(UPDATED_DONE);
        assertThat(testTask.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTask.getAttachment()).isEqualTo(UPDATED_ATTACHMENT);
        assertThat(testTask.getAttachmentContentType()).isEqualTo(UPDATED_ATTACHMENT_CONTENT_TYPE);
        assertThat(testTask.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testTask.getPictureContentType()).isEqualTo(UPDATED_PICTURE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTask() throws Exception {
        int databaseSizeBeforeUpdate = taskRepository.findAll().size();

        // Create the Task
        TaskDTO taskDTO = taskMapper.toDto(task);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskMockMvc.perform(put("/api/tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Task in the database
        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTask() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        int databaseSizeBeforeDelete = taskRepository.findAll().size();

        // Delete the task
        restTaskMockMvc.perform(delete("/api/tasks/{id}", task.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Task.class);
        Task task1 = new Task();
        task1.setId(1L);
        Task task2 = new Task();
        task2.setId(task1.getId());
        assertThat(task1).isEqualTo(task2);
        task2.setId(2L);
        assertThat(task1).isNotEqualTo(task2);
        task1.setId(null);
        assertThat(task1).isNotEqualTo(task2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskDTO.class);
        TaskDTO taskDTO1 = new TaskDTO();
        taskDTO1.setId(1L);
        TaskDTO taskDTO2 = new TaskDTO();
        assertThat(taskDTO1).isNotEqualTo(taskDTO2);
        taskDTO2.setId(taskDTO1.getId());
        assertThat(taskDTO1).isEqualTo(taskDTO2);
        taskDTO2.setId(2L);
        assertThat(taskDTO1).isNotEqualTo(taskDTO2);
        taskDTO1.setId(null);
        assertThat(taskDTO1).isNotEqualTo(taskDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(taskMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(taskMapper.fromId(null)).isNull();
    }
}
