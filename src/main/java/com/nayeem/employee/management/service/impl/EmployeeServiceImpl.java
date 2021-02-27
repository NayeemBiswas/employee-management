/**

 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package com.nayeem.employee.management.service.impl
 */
package com.nayeem.employee.management.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nayeem.employee.management.common.exception.CustomDataIntegrityViolationException;
import com.nayeem.employee.management.common.exception.InsuficientBalanceException;
import com.nayeem.employee.management.common.exception.SalaryNotSetException;
import com.nayeem.employee.management.common.message.BaseResponse;
import com.nayeem.employee.management.common.message.CustomMessage;
import com.nayeem.employee.management.common.utill.CopyProperties;
import com.nayeem.employee.management.model.dto.EmployeeDto;
import com.nayeem.employee.management.model.dto.SalarySheetDto;
import com.nayeem.employee.management.model.entity.BankAccount;
import com.nayeem.employee.management.model.entity.Employee;
import com.nayeem.employee.management.repository.BankAccountRepository;
import com.nayeem.employee.management.repository.CompanyBalanceRepository;
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
	
	@Autowired
	private CompanyBalanceService companyBalanceService;
	
	private Double paidammount = 0.00;

	@Override
	public EmployeeDto getEmployeeInfo(String email) {
		Employee employee = employeeRepository.findByEmail(email);
		return copyProperties.copyEmployeeEntityToDto(employee);
	}

	@Override
	public BaseResponse saveOrUpdateEmployeeInfo(EmployeeDto employeeDto) {

		Employee employee = copyProperties.copyEmployeeDtoToEntity(employeeDto);

		if (employeeDto.getId() == null) {

			if (employeeDto.getAccount().getId() == null) {
				BankAccount account = copyProperties.copyBankAccountDtoToEntity(employeeDto.getAccount());
				try {
					bankAccountRepository.save(account);
					employee.setAccount(account);
					employee.setGrade(employeeDto.getGrade().toLowerCase());
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
		if(employee.getAccount().getCurrentBalance()> 0)
		{
			throw new SalaryNotSetException("Set Salary First");
		}else if (companyBalanceService.currentBalance()<employee.getSalary()) {
			throw new InsuficientBalanceException(CustomMessage.INSUFICIENT_BALANCE);
		}else {
			employee.getAccount().setCurrentBalance(employee.getAccount().getCurrentBalance() + employee.getSalary());
			bankAccountRepository.save(employee.getAccount());
			companyBalanceService.withdrowBalance(employee.getSalary());
		}
		
	}

	@Override
	public BaseResponse setSalary(Double amount) {
		List<Employee> employees = employeeRepository.findAll();
		
		try {
			employees.stream().forEach(employee -> {
				double basicSalary = 0;
				
				if(employee.getGrade().equals("six"))
				{
					basicSalary = amount;
				}else if(employee.getGrade().equals("five"))
				{
					basicSalary = amount + 5000.00;
				}else if(employee.getGrade().equals("four"))
				{
					basicSalary = amount + 10000.00;
				}else if(employee.getGrade().equals("three"))
				{
					basicSalary = amount + 15000.00;
				}else if(employee.getGrade().equals("two"))
				{
					basicSalary = amount + 20000.00;
				}else if(employee.getGrade().equals("one"))
				{
					basicSalary = amount + 25000.00;
				}
				employee.setSalary(basicSalary + (basicSalary * 0.35));
				employeeRepository.save(employee);
			});
			
			return new BaseResponse(CustomMessage.SALARY_SET, HttpStatus.CREATED.value());
			
		} catch (Exception e) {
			throw new CustomDataIntegrityViolationException("Salary Set Failed");
		}
		
	}

}
