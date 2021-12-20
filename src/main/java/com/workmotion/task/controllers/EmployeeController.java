package com.workmotion.task.controllers;

import com.workmotion.task.dtos.CreateEmployeeDto;
import com.workmotion.task.entities.Employee;
import com.workmotion.task.services.EmployeeService;
import com.workmotion.task.states.EmployeeState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;
  /**
   * An end-point for employee creation with initial state (ADDED)
   *
   * @param dto with attributes (name, contractInfo, birthdate)
   * @return created (201)
   */
  @PostMapping
  public ResponseEntity<Employee> createEmployee(CreateEmployeeDto dto) {

    return new ResponseEntity<>(employeeService.createEmployee(dto), HttpStatus.CREATED);
  }

  @PatchMapping("/{state}")
  public ResponseEntity<Void> updateEmployeeState(@PathVariable EmployeeState state) {
    employeeService.updateEmployeeState(state);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<Page<Employee>> getEmployees(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(employeeService.getEmployees(pageable));
  }
}
