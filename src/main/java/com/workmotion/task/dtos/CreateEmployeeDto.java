package com.workmotion.task.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@Builder
public class CreateEmployeeDto implements Serializable {

  @NotBlank(message = "Employee name must be provided!")
  private String name;

  private String contractInfo;

  @NotNull(message = "Birthdate must be provided!")
  private LocalDate birthdate;
}
