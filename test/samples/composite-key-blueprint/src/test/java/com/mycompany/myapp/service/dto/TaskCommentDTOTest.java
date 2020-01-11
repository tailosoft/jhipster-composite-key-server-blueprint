package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TaskCommentDTOTest {

    @Test
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
}
