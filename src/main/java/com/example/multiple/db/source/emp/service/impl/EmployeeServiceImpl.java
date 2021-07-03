package com.example.multiple.db.source.emp.service.impl;

import com.example.multiple.db.source.emp.entity.Employee;
import com.example.multiple.db.source.emp.repository.EmployeeRepository;
import com.example.multiple.db.source.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //@Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return null;
    }
}
