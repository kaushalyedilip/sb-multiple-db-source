package com.example.multiple.db.source.emp.repository;

import com.example.multiple.db.source.emp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
