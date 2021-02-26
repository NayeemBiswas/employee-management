/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package employee-management
 */
package com.nayeem.employee.management.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "employee")
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Column(name = "name")
	@Size(max = 50, message = "Name Can't be more than {max}")
	private String name;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Column(name = "email", unique = true)
	@Size(max = 50, message = "Email Can't be more than {max}")
	private String email;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Size(max = 20, message = "Phone Can't be more than {max}")
	@Column(name = "phone")
	private String phone;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Column(name = "phone")
	@Size(max = 10, message = "Grade Can't be more than {max}")
//	@Digits(fraction = 0, integer = 1)
	private String grade;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Size(max = 20, message = "Address Can't be more than {max}")
	@Column(name = "address")
	private String address;
	
	@Column(name = "salary", nullable = true)
	private Double salary;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@JoinColumn(name = "account", referencedColumnName = "id", unique = true)
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private BankAccount account;

}
