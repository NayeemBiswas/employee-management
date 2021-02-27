/**
 * @Since Feb 27, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package com.nayeem.employee.management
 */
package com.nayeem.employee.management.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nayeem.employee.management.common.message.BaseResponse;
import com.nayeem.employee.management.model.dto.EmployeeDto;
import com.nayeem.employee.management.model.dto.SalarySheetDto;
import com.nayeem.employee.management.service.EmployeeService;


/**
 * @author Nayeem
 *
 */

@RequestMapping("/emp")
@RestController
@Validated
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService; 
	
	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		BaseResponse response = employeeService.saveOrUpdateEmployeeInfo(employeeDto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/profile")
	public EmployeeDto employeeInfo(String email)
	{
		return employeeService.getEmployeeInfo(email);
	}
	
	@PostMapping(value = "/trans-salary")
	public ResponseEntity<BaseResponse> salaryTransfer() {
		BaseResponse response = employeeService.transferSalary();
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value = "/salary-sheet")
	public ResponseEntity<List<SalarySheetDto>> salarySheet() {
		List<SalarySheetDto> response = employeeService.salarySheet();
		return new ResponseEntity<List<SalarySheetDto>>(response, HttpStatus.OK);
	}
	

	@PostMapping(value = "/set-salary/{amount}")
	public ResponseEntity<BaseResponse> setSalary(@PathVariable("amount") Double amount) {
		BaseResponse response = employeeService.setSalary(amount);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}

}











