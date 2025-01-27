package com.javaguide.employee_service.mapper;

import com.javaguide.employee_service.dto.EmployeeDto;
import com.javaguide.employee_service.entity.Employee;

public class EmployeeMapper {

    public static Employee mapToEntity(EmployeeDto employeeDto){
        Employee emp = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
        return emp;
    }

    public static EmployeeDto mapToDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
        return employeeDto;
    }
}
