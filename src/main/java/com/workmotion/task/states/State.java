package com.workmotion.task.states;

import com.workmotion.task.entities.Employee;
import com.workmotion.task.states.enums.EmployeeState;

public interface State {

  static void next(Employee employee) {
    switch (employee.getState()) {
      case ADDED:
        employee.changeState(EmployeeState.IN_CHECK);
        break;
      case IN_CHECK:
        employee.changeState(EmployeeState.APPROVED);
        break;
      case APPROVED:
        employee.changeState(EmployeeState.ACTIVE);
        break;
    }
  }

  static void previous(Employee employee) {
    switch (employee.getState()) {
      case IN_CHECK:
        employee.changeState(EmployeeState.ADDED);
        break;
      case APPROVED:
        employee.changeState(EmployeeState.IN_CHECK);
        break;
      case ACTIVE:
        employee.changeState(EmployeeState.APPROVED);
        break;
    }
  }
}
