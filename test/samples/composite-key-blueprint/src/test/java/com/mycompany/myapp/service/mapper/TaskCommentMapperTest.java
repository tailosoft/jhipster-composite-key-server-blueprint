package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TaskCommentMapperTest {

    private TaskCommentMapper taskCommentMapper;

    @BeforeEach
    public void setUp() {
        taskCommentMapper = new TaskCommentMapperImpl();
    }

    @Test
        public void testEntityFromId() {
        assertThat(taskCommentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(taskCommentMapper.fromId(null)).isNull();
    }
}
