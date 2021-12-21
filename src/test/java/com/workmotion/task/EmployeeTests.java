package com.workmotion.task;

import com.workmotion.task.controllers.EmployeeController;
import com.workmotion.task.dtos.CreateEmployeeDto;
import com.workmotion.task.repos.EmployeeRepo;
import com.workmotion.task.services.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeTests extends WorkmotionTaskApplicationTests {
  @Autowired private EmployeeController employeeController;
  @Autowired private EmployeeRepo employeeRepo;
  @Autowired private EmployeeService employeeService;

  @Test
  @DisplayName("Ensure that employee controller is available in the bean pool")
  @Order(1)
  public void employeeControllerTest() {
    assertThat(employeeController).isNotNull();
  }

  @Test
  @DisplayName("Ensure that employee repo is available in the bean pool")
  @Order(2)
  public void employeeRepoTest() {
    assertThat(employeeRepo).isNotNull();
  }

  @Test
  @DisplayName("Ensure that employee service is available in the bean pool")
  @Order(3)
  public void employeeServiceTest() {
    assertThat(employeeService).isNotNull();
  }

  @Test
  @DisplayName("Create employee - Happy scenario")
  @Order(4)
  public void createEmployeeValid() throws Exception {

    CreateEmployeeDto employeeDto =
        CreateEmployeeDto.builder()
            .name("Jack")
            .contractInfo("Full-time")
            .birthdate(LocalDate.of(1995, 6, 14))
            .build();

    this.mockMvc
        .perform(
            post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectAsJson(employeeDto)))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Get paginated response - no filters - default pageable")
  @Order(5)
  public void getPaginatedEmployeeResponse() throws Exception {
    this.mockMvc
        .perform(get("/employee"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray());
  }
}
