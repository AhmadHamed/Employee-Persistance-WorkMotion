package com.workmotion.task.entities;

import com.workmotion.task.states.EmployeeState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  private String name;
  private String contractInfo;
  private LocalDate birthdate;
  private EmployeeState state;
}
