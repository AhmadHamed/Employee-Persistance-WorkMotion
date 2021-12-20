package com.workmotion.task.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CreateEmployeeDto {
  private String name;
  private String contractInfo;
  private LocalDate birthdate;
}
