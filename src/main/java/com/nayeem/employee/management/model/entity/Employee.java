/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package com.nayeem.employee.management.model.entity
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
	@GenericGenerator(name = "employee_seq",strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	parameters = {@Parameter(name = "sequence_name", value = "employee_sequence"),
			@Parameter(name = "initial_value", value = "1000"),
	        @Parameter(name = "increment_size", value = "1")
	})        
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
	@Column(name = "grade")
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
