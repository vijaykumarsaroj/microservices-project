package com.javaguide.employee_service.service;

import com.javaguide.employee_service.dto.APIResponseDto;
import com.javaguide.employee_service.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployee(Long employeeId);
}
