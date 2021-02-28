/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package com.nayeem.employee.management.model.entity
 */
package com.nayeem.employee.management.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nayeem.employee.management.common.message.CustomMessage;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Table(name = "bank_account")
@Entity
public class BankAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Column(name = "account_type")
	@Size(max = 50, message = "Account Type Can't be more than {max}")
	private String accountType;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Column(name = "account_name")
	@Size(max = 100, message = "Account Name Can't be more than {max}")
	private String accountName;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Column(name = "account_number")
	@Size(max = 50, message = "Account Number Can't be more than {max}")
	private String accountNumber;
	
	@Column(name = "current_balance", nullable = true)
	@Digits(fraction = 2, integer = 9)
	private Double currentBalance;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Column(name = "bank_name")
	@Size(max = 200, message = "Bank Name Can't be more than {max}")
	private String bankName;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Column(name = "branch_name")
	@Size(max = 100, message = "Branch Name Can't be more than {max}")
	private String branchName;

}
