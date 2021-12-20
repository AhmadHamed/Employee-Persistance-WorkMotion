package com.workmotion.task.services;

import com.workmotion.task.dtos.CreateEmployeeDto;
import com.workmotion.task.entities.Employee;
import com.workmotion.task.states.EmployeeState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

  Employee createEmployee(CreateEmployeeDto dto);

  void updateEmployeeState(EmployeeState state);

  Page<Employee> getEmployees(Pageable pageable);
}
