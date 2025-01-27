package com.javaguide.employee_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponseDto {
    private EmployeeDto employee;
    private DepartmentDto department;

    public APIResponseDto(){}
    public APIResponseDto(EmployeeDto employee, DepartmentDto department) {
        this.employee = employee;
        this.department = department;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }
}
