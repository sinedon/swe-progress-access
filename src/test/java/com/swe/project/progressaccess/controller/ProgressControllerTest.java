package com.swe.project.progressaccess.controller;

import com.swe.project.progressaccess.service.ProgressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProgressController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ProgressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProgressService service;

    @Test
    void recordClickCallsServiceAndReturnsOk() throws Exception {
        String requestBody = """
                {
                  "learnerId": "learner-1",
                  "topicId": "fruits",
                  "label": "Apple"
                }
                """;

        mockMvc.perform(post("/progress/click")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        verify(service).recordClick("learner-1", "fruits", "Apple");
    }

    @Test
    void getProgressReturnsLearnerProgress() throws Exception {
        when(service.getProgress("learner-1")).thenReturn(List.of());

        mockMvc.perform(get("/progress/learner-1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(service).getProgress("learner-1");
    }

    @Test
    void getProgressForTopicReturnsTopicProgress() throws Exception {
        when(service.getProgressForTopic("learner-1", "fruits")).thenReturn(List.of());

        mockMvc.perform(get("/progress/learner-1/topic/fruits"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(service).getProgressForTopic("learner-1", "fruits");
    }

    @Test
    void undoLatestClickReturnsNoContentWhenClickExists() throws Exception {
        when(service.undoLatestClick("learner-1", "fruits")).thenReturn(true);

        mockMvc.perform(delete("/progress/learner-1/topic/fruits/latest"))
                .andExpect(status().isNoContent());

        verify(service).undoLatestClick("learner-1", "fruits");
    }

    @Test
    void undoLatestClickReturnsNotFoundWhenClickDoesNotExist() throws Exception {
        when(service.undoLatestClick("learner-1", "fruits")).thenReturn(false);

        mockMvc.perform(delete("/progress/learner-1/topic/fruits/latest"))
                .andExpect(status().isNotFound());

        verify(service).undoLatestClick("learner-1", "fruits");
    }
}
