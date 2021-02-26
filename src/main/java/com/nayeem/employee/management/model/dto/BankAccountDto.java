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
public class BankAccountDto {

	private Long id;

	private String accountType;

	private String accountName;

	private String accountNumber;

	private Double currentBbalance;

	private String bankName;

	private String branchName;

}
