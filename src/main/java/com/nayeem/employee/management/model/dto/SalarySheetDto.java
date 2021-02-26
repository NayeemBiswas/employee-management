/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package employee-management
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
public class SalarySheetDto {
	
	private String name;
	
	private String grade;
	
	private Double salary;

}
