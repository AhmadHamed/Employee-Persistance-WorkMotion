package com.workmotion.task.services.impl;

import com.workmotion.task.dtos.CreateEmployeeDto;
import com.workmotion.task.entities.Employee;
import com.workmotion.task.factories.EmployeeFactory;
import com.workmotion.task.repos.EmployeeRepo;
import com.workmotion.task.services.EmployeeService;
import com.workmotion.task.states.EmployeeState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepo employeeRepo;

  @Override
  public Employee createEmployee(CreateEmployeeDto dto) {
    log.info(
        "A new employee with the following data: {} is about to be created at: {}",
        dto,
        LocalDateTime.now());

    Employee createdEmployee = EmployeeFactory.getEmployee(dto);
    return employeeRepo.save(createdEmployee);
  }

  @Override
  public void updateEmployeeState(EmployeeState state) {}

  @Override
  public Page<Employee> getEmployees(Pageable pageable) {
    log.info(
        "Employees Page: {} of size: {} of Direction: {} is about to be fetched at: {}",
        pageable.getPageNumber(),
        pageable.getPageSize(),
        pageable.getSort(),
        LocalDateTime.now());

    return employeeRepo.findAll(pageable);
  }
}
