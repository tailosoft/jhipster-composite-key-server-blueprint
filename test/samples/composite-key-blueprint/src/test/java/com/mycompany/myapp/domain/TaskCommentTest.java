package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TaskCommentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskComment.class);
        TaskComment taskComment1 = new TaskComment();
        taskComment1.setId(1L);
        TaskComment taskComment2 = new TaskComment();
        taskComment2.setId(1L);
        assertThat(taskComment1).isEqualTo(taskComment2);
        taskComment2.setId(2L);
        assertThat(taskComment1).isNotEqualTo(taskComment2);
        taskComment1.setId(null);
        assertThat(taskComment1).isNotEqualTo(taskComment2);
    }
}
