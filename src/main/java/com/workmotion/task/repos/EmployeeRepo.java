package com.workmotion.task.repos;

import com.workmotion.task.entities.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, Long> {
  Optional<Employee> findByName(String name);
}
