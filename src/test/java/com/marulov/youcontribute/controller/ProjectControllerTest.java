package com.marulov.youcontribute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marulov.youcontribute.converter.project.ProjectDtoConverter;
import com.marulov.youcontribute.dto.project.CreateProjectRequest;
import com.marulov.youcontribute.dto.project.ProjectDto;
import com.marulov.youcontribute.model.Project;
import com.marulov.youcontribute.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProjectController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private ProjectService projectService;
    @MockBean
    private ProjectDtoConverter projectDtoConverter;

    @Test
    public void it_should_list_projects() throws Exception {

        //given
        Project project = Project.builder().id(1L).organization("Marulov").repository("you-Marulov").build();
        ProjectDto projectDto = ProjectDto.builder().id(1L).organization("Marulov").repository("you-contribute").build();

        //when
        when(projectService.getAll()).thenReturn(Collections.singletonList(projectDto));
        when(projectDtoConverter.convert(Collections.singletonList(project))).thenReturn(Collections.singletonList(projectDto));

        //then
        mockMvc.perform(get("/api/v1/projects")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].organization").value("Marulov"))
                .andExpect(jsonPath("$[0].repository").value("you-contribute"));
    }

    @Test
    public void it_should_create_project() throws Exception {

        //given
        CreateProjectRequest request = CreateProjectRequest.builder().organization("Marulov").repository("you-contribute").build();

        //when
        doNothing().when(projectService).create(request);

        //then
        mockMvc.perform(post("/api/v1/projects").content(objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
    }
}