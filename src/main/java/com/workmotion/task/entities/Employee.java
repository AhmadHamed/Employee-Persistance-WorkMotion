package com.workmotion.task.entities;

import com.workmotion.task.states.EmployeeState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  private String name;
  private String contractInfo;
  private Integer age;
  private EmployeeState state;

  public Employee(String name, String contractInfo, Integer age, EmployeeState state) {
    this.name = name;
    this.contractInfo = contractInfo;
    this.age = age;
    this.state = state;
  }
}
