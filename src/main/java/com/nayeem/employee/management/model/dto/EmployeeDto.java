/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package com.nayeem.employee.management.model.dto
 */
package com.nayeem.employee.management.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Nayeem
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {

	private Long id;

	private String name;

	private String email;

	private String phone;

	private String grade;

	private String address;
	
	private BankAccountDto account;

}
