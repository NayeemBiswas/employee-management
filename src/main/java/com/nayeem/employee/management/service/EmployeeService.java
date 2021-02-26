/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package employee-management
 */
package com.nayeem.employee.management.service;

import java.util.List;

import com.nayeem.employee.management.common.message.BaseResponse;
import com.nayeem.employee.management.model.dto.EmployeeDto;
import com.nayeem.employee.management.model.dto.SalarySheetDto;

/**
 * @author Nayeem
 *
 */
public interface EmployeeService {
	
	public EmployeeDto getEmployeeInfo(String email);
	
	public BaseResponse saveOrUpdateEmployeeInfo(EmployeeDto employeeDto);
	
	public BaseResponse transferSalary();
	
	public List<SalarySheetDto> salarySheet();

}
