package com.springboot.web.apiEmployee;

import com.springboot.web.apiEmployee.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceStub {
    private static Map<Long, Employee> employees=new HashMap<>();
    private static Long index=2L;

    static {
        Employee employeeOne=new Employee(1L,"Rim","123456786",321456L);
        Employee employeeTwo=new Employee(2L,"sim","7989456123",32346L);
        employees.put(1L,employeeOne);
        employees.put(2L,employeeTwo);
    }

    public static List<Employee> getAllEmployee(){
        return new ArrayList<>(employees.values());
    }

    public static Employee getEmployeeById(Long employeeId){
        return employees.get(employeeId);
    }

    public static Employee addEmployee(Employee employee){
        index=index+1;
        employee.setId(index);
        employees.put(index,employee);
        return employee;
    }

    public static Employee updateEmployee(Long employeeId,Employee employee){
        employee.setId(employeeId);
        employees.put(employeeId,employee);
        return employee;
    }

    public static Employee deleteEmployee(Long employeeId){
        return employees.remove(employeeId);
    }
}
