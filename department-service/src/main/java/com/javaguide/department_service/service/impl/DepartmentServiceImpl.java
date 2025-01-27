package com.javaguide.department_service.service.impl;

import com.javaguide.department_service.dto.DepartmentDto;
import com.javaguide.department_service.entity.Department;
import com.javaguide.department_service.mapper.DepartmentMapper;
import com.javaguide.department_service.repository.DepartmentRepository;
import com.javaguide.department_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Department department = DepartmentMapper.mapToEntity(departmentDto);
        Department dept = departmentRepository.save(department);
        return DepartmentMapper.mapToDto(dept);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        return DepartmentMapper.mapToDto(department);
    }
}
