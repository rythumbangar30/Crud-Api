package com.springboot.web.apiEmployee;

import org.springframework.web.bind.annotation.*;

import java.util.List;
//@RestController
public class EmployeeController {
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return EmployeeServiceStub.getAllEmployee();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId){
        return EmployeeServiceStub.getEmployeeById(employeeId);
    }

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee){
        return EmployeeServiceStub.addEmployee(employee);
    }

    @PutMapping("/updateEmployee/{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId,@RequestBody Employee employee){
        return EmployeeServiceStub.updateEmployee(employeeId,employee);
    }

    @DeleteMapping("/deleteEmployee/{employeeId}")
    public Employee deleteEmployee(@PathVariable Long employeeId){
        return EmployeeServiceStub.deleteEmployee(employeeId);
    }
}
