package com.javaguide.employee_service.controller;

import com.javaguide.employee_service.dto.APIResponseDto;
import com.javaguide.employee_service.dto.EmployeeDto;
import com.javaguide.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto emp = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("id") Long employeeId){
        APIResponseDto apiResponseDto = employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
    }
}
