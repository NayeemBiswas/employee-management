/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package com.nayeem.employee.management.common.utill
 */
package com.nayeem.employee.management.common.utill;

import org.springframework.beans.BeanUtils;

import com.nayeem.employee.management.model.dto.BankAccountDto;
import com.nayeem.employee.management.model.dto.EmployeeDto;
import com.nayeem.employee.management.model.dto.SalarySheetDto;
import com.nayeem.employee.management.model.entity.BankAccount;
import com.nayeem.employee.management.model.entity.Employee;

/**
 * @author Nayeem
 *
 */
public class CopyProperties {

	/**
	 * @param employee
	 * @return EmployeeDto
	 */
	public EmployeeDto copyEmployeeEntityToDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();

		BeanUtils.copyProperties(employee, employeeDto);

		employeeDto.setAccount(copyBankAccountEntityToDto(employee.getAccount()));

		return employeeDto;
	}

	/**
	 * @param bankAccount
	 * @return BankAccountDto
	 */
	public BankAccountDto copyBankAccountEntityToDto(BankAccount bankAccount) {
		BankAccountDto accountDto = new BankAccountDto();

		BeanUtils.copyProperties(bankAccount, accountDto);
		return accountDto;
	}

	/**
	 * @param employeeDto
	 * @return Employee
	 */
	public Employee copyEmployeeDtoToEntity(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		return employee;
	}

	/**
	 * @param bankAccountDto
	 * @return BankAccount
	 */
	public BankAccount copyBankAccountDtoToEntity(BankAccountDto bankAccountDto) {
		BankAccount account = new BankAccount();
		BeanUtils.copyProperties(bankAccountDto, account);
		return account;
	}
	
	public SalarySheetDto setSalarySheet(Employee employee)
	{
		SalarySheetDto salarySheetDto = new SalarySheetDto();
		
		salarySheetDto.setGrade(employee.getGrade());
		salarySheetDto.setName(employee.getName());
		salarySheetDto.setSalary(employee.getSalary());
		
		return salarySheetDto;
	}

}
