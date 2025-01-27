package com.javaguide.department_service.mapper;

import com.javaguide.department_service.dto.DepartmentDto;
import com.javaguide.department_service.entity.Department;

public class DepartmentMapper {

    public static Department mapToEntity(DepartmentDto departmentDto){
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        return department;
    }

    public static DepartmentDto mapToDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }
}
