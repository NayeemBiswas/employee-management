/**

 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package employee-management
 */
package com.nayeem.employee.management.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nayeem.employee.management.common.exception.CustomDataIntegrityViolationException;
import com.nayeem.employee.management.common.exception.SalaryNotSetException;
import com.nayeem.employee.management.common.message.BaseResponse;
import com.nayeem.employee.management.common.message.CustomMessage;
import com.nayeem.employee.management.common.utill.CopyProperties;
import com.nayeem.employee.management.model.dto.EmployeeDto;
import com.nayeem.employee.management.model.dto.SalarySheetDto;
import com.nayeem.employee.management.model.entity.BankAccount;
import com.nayeem.employee.management.model.entity.Employee;
import com.nayeem.employee.management.repository.BankAccountRepository;
import com.nayeem.employee.management.repository.EmployeeRepository;
import com.nayeem.employee.management.service.EmployeeService;

/**
 * @author Nayeem
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	private CopyProperties copyProperties;
	
	private Double paidammount = 0.00;

	@Override
	public EmployeeDto getEmployeeInfo(String email) {
		Employee employee = employeeRepository.findByEmail(email);
		return copyProperties.copyEmployeeEntityToDto(employee);
	}

	@Override
	public BaseResponse saveOrUpdateEmployeeInfo(EmployeeDto employeeDto) {

		Employee employee = copyProperties.copyEmployeeDtoToEntity(employeeDto);

		if (employeeDto.getId().equals(null)) {

			if (employeeDto.getAccount().getId().equals(null)) {
				BankAccount account = copyProperties.copyBankAccountDtoToEntity(employeeDto.getAccount());
				try {
					bankAccountRepository.save(account);
					employee.setAccount(account);
					employeeRepository.save(employee);
					return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

				} catch (DataIntegrityViolationException e) {
					throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
				}
			}
			else {
				return new BaseResponse(CustomMessage.SAVE_FAILED_MESSAGE, HttpStatus.NOT_ACCEPTABLE.value());
			}
		} else {
			BankAccount account = copyProperties.copyBankAccountDtoToEntity(employeeDto.getAccount());
			try {
				bankAccountRepository.save(account);
				employee.setAccount(account);
				employeeRepository.save(employee);
				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

			} catch (DataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
			}
		}
		
	}
	
	

	@Override
	public BaseResponse transferSalary() {
		
		List<Employee> employees = employeeRepository.findAll();
		
		try {
			
		employees.stream().forEach(employee -> {
			addAmmountInEmployeAccount(employee);
			paidammount += employee.getSalary();
		});
		return new BaseResponse("Total Transfer Ammount" + paidammount , HttpStatus.CREATED.value());
		}catch (SalaryNotSetException e) {
			throw new SalaryNotSetException("Set Salary First");
		}
	}

	@Override
	public List<SalarySheetDto> salarySheet() {
		
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map(employee -> copyProperties.setSalarySheet(employee)).collect(Collectors.toList());
	}
	
	public void addAmmountInEmployeAccount(Employee employee)
	{		
		if(employee.getAccount().getCurrentBalance().equals(0))
		{
			throw new SalaryNotSetException("Set Salary First");
		}
		employee.getAccount().setCurrentBalance(employee.getAccount().getCurrentBalance() + employee.getSalary());
		bankAccountRepository.save(employee.getAccount());
	}

}
