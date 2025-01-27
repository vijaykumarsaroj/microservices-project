package com.javaguide.employee_service.service.impl;

import com.javaguide.employee_service.dto.APIResponseDto;
import com.javaguide.employee_service.dto.DepartmentDto;
import com.javaguide.employee_service.dto.EmployeeDto;
import com.javaguide.employee_service.entity.Employee;
import com.javaguide.employee_service.exception.ResourceNotFoundException;
import com.javaguide.employee_service.mapper.EmployeeMapper;
import com.javaguide.employee_service.repository.EmployeeRepository;
import com.javaguide.employee_service.service.APIClient;
import com.javaguide.employee_service.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private APIClient apiClient;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee emp = EmployeeMapper.mapToEntity(employeeDto);
        Employee saveEmp = employeeRepository.save(emp);

        return EmployeeMapper.mapToDto(saveEmp);
    }

   // @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployee(Long employeeId) {

        LOGGER.info("inside getEmployeeById() method");
        Employee emp = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("this employeeId does not exist in given :"+employeeId)
        );

        // this is used to access rest template
//        ResponseEntity<DepartmentDto>responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+ emp.getDepartmentCode(),
//                DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

        //this is using web client

        DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/departments/"+emp.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        //this is using spring cloud open feign

//        DepartmentDto departmentDto = apiClient.getDepartmentByCode(emp.getDepartmentCode());

        EmployeeDto employeeDto = EmployeeMapper.mapToDto(emp);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId,Exception exception){
        LOGGER.info("inside getEmployeeById() method");
        Employee emp = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("this employeeId does not exist in given :"+employeeId)
        );

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setDepartmentName("R&D Departments");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        EmployeeDto employeeDto = EmployeeMapper.mapToDto(emp);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
