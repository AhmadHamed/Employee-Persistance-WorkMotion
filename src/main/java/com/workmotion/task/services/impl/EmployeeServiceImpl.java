package com.workmotion.task.services.impl;

import com.workmotion.task.dtos.CreateEmployeeDto;
import com.workmotion.task.entities.Employee;
import com.workmotion.task.services.EmployeeService;
import com.workmotion.task.states.EmployeeState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Override
  public Employee createEmployee(CreateEmployeeDto dto) {
    return null;
  }

  @Override
  public void updateEmployeeState(EmployeeState state) {}

  @Override
  public Page<Employee> getEmployees(Pageable pageable) {
    return null;
  }
}
