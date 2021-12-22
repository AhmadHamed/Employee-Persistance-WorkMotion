package com.workmotion.task.services.impl;

import com.workmotion.task.dtos.CreateEmployeeDto;
import com.workmotion.task.entities.Employee;
import com.workmotion.task.factories.EmployeeFactory;
import com.workmotion.task.repos.EmployeeRepo;
import com.workmotion.task.services.EmployeeService;
import com.workmotion.task.states.State;
import com.workmotion.task.states.enums.StateDirection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
  public Page<Employee> getEmployees(Pageable pageable) {
    log.info(
        "Employees Page: {} of size: {} of Direction: {} is about to be fetched at: {}",
        pageable.getPageNumber(),
        pageable.getPageSize(),
        pageable.getSort(),
        LocalDateTime.now());

    return employeeRepo.findAll(pageable);
  }

  @Override
  public void updateEmployeeState(String name, StateDirection stateDirection) {
    Employee employee = getEmployeeByName(name);
    log.info(
        "State of Employee: {} is about to be pushed in the: {} direction at: {}",
        employee,
        stateDirection.name(),
        LocalDateTime.now());
    if (stateDirection.equals(StateDirection.NEXT)) {
      State.next(employee);
    } else {
      State.previous(employee);
    }
    employeeRepo.save(employee);
  }

  private Employee getEmployeeByName(String name) {
    return employeeRepo
        .findByName(name)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
  }
}
