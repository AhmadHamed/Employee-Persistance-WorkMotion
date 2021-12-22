package com.workmotion.task.entities;

import com.workmotion.task.states.enums.EmployeeState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  private String name;
  private String contractInfo;
  private Integer age;
  private EmployeeState state;
  @Version private Long version;

  public Employee(String name, String contractInfo, Integer age, EmployeeState state) {
    this.name = name;
    this.contractInfo = contractInfo;
    this.age = age;
    this.state = state;
  }

  public void changeState(EmployeeState state) {
    this.state = state;
  }
}
