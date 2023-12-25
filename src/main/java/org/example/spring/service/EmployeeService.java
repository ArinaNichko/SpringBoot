package org.example.spring.service;

import org.example.spring.exception.CustomRuntimeException;
import org.example.spring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toMap;

@Service
public class EmployeeService {

    private final ConcurrentHashMap<Integer, Employee> employeeMap = new ConcurrentHashMap<>();


    public List<Employee> getAll() {
        return new ArrayList<>(employeeMap.values());
    }

    public Employee getById(Integer id) {
        if (employeeMap.get(id) == null) {
            throw new CustomRuntimeException("Employee cannot be null");
        } if (!employeeMap.containsKey(id)) {
            throw new CustomRuntimeException(String.format("Stored employee was not found with particular id: %s", id));
        }
        return employeeMap.getOrDefault(id, null);

    }

    public Employee add(Employee employee) {
        if (employeeMap.containsKey(employee.getId())) {
            throw new CustomRuntimeException(String.format("Employee was found with particular id: %s", employee.getId()));
        }
        employeeMap.put(employee.getId(), employee);
        return employeeMap.get(employee.getId());
    }

    public void addList(List<Employee> employees) {
        Map<Integer, Employee> allEmployee = employees.stream()
                .collect(toMap(Employee::getId, employee -> employee));
        employeeMap.putAll(allEmployee);
    }

    public void remove(Integer id) {
        if (!employeeMap.containsKey(id)) {
            throw new CustomRuntimeException(String.format("Stored employee was not found with particular id: %s", id));
        }
        employeeMap.remove(id);
    }
}
