package com.workmotion.task;

import com.workmotion.task.controllers.EmployeeController;
import com.workmotion.task.dtos.CreateEmployeeDto;
import com.workmotion.task.entities.Employee;
import com.workmotion.task.factories.EmployeeFactory;
import com.workmotion.task.repos.EmployeeRepo;
import com.workmotion.task.services.EmployeeService;
import com.workmotion.task.states.enums.EmployeeState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    CreateEmployeeDto employeeDto = getCreateEmployeeDto();

    this.mockMvc
        .perform(
            post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectAsJson(employeeDto)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is(employeeDto.getName())))
        .andExpect(jsonPath("$.contractInfo", is(employeeDto.getContractInfo())))
        .andExpect(
            jsonPath("$.age", is(LocalDate.now().getYear() - employeeDto.getBirthdate().getYear())))
        .andExpect(jsonPath("$.state", is(EmployeeState.ADDED.name())));
  }

  @Test
  @DisplayName("Get paginated response - no filters - default pageable")
  @Order(5)
  public void getPaginatedEmployeeResponse() throws Exception {
    clearDb();
    saveMockEmployee();

    this.mockMvc
        .perform(get("/employee"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.content", hasSize(1)))
        .andExpect(jsonPath("$.numberOfElements", is(1)))
        .andExpect(jsonPath("$.totalElements", is(1)))
        .andExpect(jsonPath("$.pageable").exists())
        .andExpect(jsonPath("$.pageable.pageSize", is(10)))
        .andExpect(jsonPath("$.pageable.pageNumber", is(0)));
  }

  @Test
  @DisplayName("Get paginated response - no filters - custom pageable")
  @Order(6)
  public void getPaginatedEmployeeResponseWithPageable() throws Exception {
    clearDb();
    saveMockEmployee();

    this.mockMvc
        .perform(get("/employee").param("page", "0").param("size", "5"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.content", hasSize(1)))
        .andExpect(jsonPath("$.numberOfElements", is(1)))
        .andExpect(jsonPath("$.totalElements", is(1)))
        .andExpect(jsonPath("$.pageable").exists())
        .andExpect(jsonPath("$.pageable.pageSize", is(5)))
        .andExpect(jsonPath("$.pageable.pageNumber", is(0)));
  }

  @Test
  @DisplayName("Update Employee state - ADDED - NEXT")
  @Order(7)
  public void updateEmployeeStateNext() throws Exception {
    clearDb();
    Employee savedEmployee = saveMockEmployee();

    this.mockMvc
        .perform(patch("/employee/" + savedEmployee.getName() + "/NEXT"))
        .andDo(print())
        .andExpect(status().isOk());

    Optional<Employee> employee = employeeRepo.findByName(savedEmployee.getName());
    assertTrue(employee.isPresent());
    assertEquals(EmployeeState.IN_CHECK, employee.get().getState());
  }

  @Test
  @DisplayName("Update Employee state - ACTIVE - PREVIOUS")
  @Order(8)
  public void updateEmployeeStatePrevious() throws Exception {
    clearDb();
    Employee savedEmployee = saveMockEmployee();
    savedEmployee.changeState(EmployeeState.ACTIVE);
    employeeRepo.save(savedEmployee);

    this.mockMvc
        .perform(patch("/employee/" + savedEmployee.getName() + "/PREVIOUS"))
        .andDo(print())
        .andExpect(status().isOk());

    Optional<Employee> employee = employeeRepo.findByName(savedEmployee.getName());
    assertTrue(employee.isPresent());
    assertEquals(EmployeeState.APPROVED, employee.get().getState());
  }

  private Employee saveMockEmployee() {
    CreateEmployeeDto employeeDto = getCreateEmployeeDto();
    return employeeRepo.save(EmployeeFactory.getEmployee(employeeDto));
  }

  private void clearDb() {
    employeeRepo.deleteAll();
  }

  private CreateEmployeeDto getCreateEmployeeDto() {
    return CreateEmployeeDto.builder()
        .name("Jack")
        .contractInfo("Full-time")
        .birthdate(LocalDate.of(1995, 6, 14))
        .build();
  }
}
