package com.workmotion.task.factories;

import com.workmotion.task.dtos.CreateEmployeeDto;
import com.workmotion.task.entities.Employee;
import com.workmotion.task.states.EmployeeState;

import java.time.LocalDate;

public abstract class EmployeeFactory {

  /** This would have a switch statement if there were different types of employees */
  public static Employee getEmployee(CreateEmployeeDto dto) {
    return new Employee(
        dto.getName(),
        dto.getContractInfo(),
        getEmployeeAge(dto.getBirthdate()),
        EmployeeState.ADDED);
  }

  private static Integer getEmployeeAge(LocalDate birthdate) {
    return LocalDate.now().getYear() - birthdate.getYear();
  }
}
