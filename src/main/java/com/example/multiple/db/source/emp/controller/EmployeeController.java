package com.example.multiple.db.source.emp.controller;

import com.example.multiple.db.source.emp.entity.Employee;
import com.example.multiple.db.source.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
}
