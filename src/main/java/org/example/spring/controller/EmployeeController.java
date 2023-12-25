package org.example.spring.controller;

import org.example.spring.exception.CustomRuntimeException;
import org.example.spring.model.Employee;
import org.example.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    @PutMapping("/add")
    public Employee add(@RequestBody Employee employee) {
        if (isNull(employee)) {
            throw new CustomRuntimeException("Employee cannot be null");
        }
        return employeeService.add(employee);
    }

    @PutMapping("/addlist")
    public List<Employee> addList(@RequestBody List<Employee> employees) {
        if (employees.isEmpty()) {
            throw new CustomRuntimeException("No employees to add!");
        }

        employeeService.addList(employees);
        return employeeService.getAll();
    }

//    @DeleteMapping("/{id}")
//    public String deleteById(@PathVariable Integer id) {
//        employeeService.remove(id);
//        return String.format("Employee with id = %s has been successfully deleted!", id);
//    }
//
//    @PatchMapping("/update")
//    public Employee updateEmployee(@RequestBody Employee employee) {
//
//        return employeeService.update(employee);
//    }
}
