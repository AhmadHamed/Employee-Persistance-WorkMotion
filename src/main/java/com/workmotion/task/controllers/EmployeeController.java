package com.workmotion.task.controllers;

import com.workmotion.task.dtos.CreateEmployeeDto;
import com.workmotion.task.entities.Employee;
import com.workmotion.task.services.EmployeeService;
import com.workmotion.task.states.enums.EmployeeState;
import com.workmotion.task.states.enums.StateDirection;
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
  public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeDto dto) {

    return new ResponseEntity<>(employeeService.createEmployee(dto), HttpStatus.CREATED);
  }

  /**
   * An end-point to get paginated employees
   *
   * @param pageable used to declare the [page, size, direction]
   * @return a page of employees
   */
  @GetMapping
  public ResponseEntity<Page<Employee>> getEmployees(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(employeeService.getEmployees(pageable));
  }

  /**
   * An end-point to update employee state to a value of [ADDED, IN_CHECK, APPROVED, ACTIVE]
   *
   * @param stateDirection must adhere to the values in {@link EmployeeState}
   * @return ok (200)
   */
  @PatchMapping("/{name}/{stateDirection}")
  public ResponseEntity<Void> updateEmployeeState(
      @PathVariable String name, @PathVariable StateDirection stateDirection) {
    employeeService.updateEmployeeState(name, stateDirection);
    return ResponseEntity.ok().build();
  }
}
