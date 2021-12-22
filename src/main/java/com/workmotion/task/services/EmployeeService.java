package com.workmotion.task.services;

import com.workmotion.task.dtos.CreateEmployeeDto;
import com.workmotion.task.entities.Employee;
import com.workmotion.task.states.enums.StateDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

  Employee createEmployee(CreateEmployeeDto dto);

  Page<Employee> getEmployees(Pageable pageable);

  void updateEmployeeState(String name, StateDirection stateDirection);
}
