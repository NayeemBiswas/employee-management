/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package employee-management
 */
package com.nayeem.employee.management.model.entity;

import java.util.Date;

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
@Table(name = "company_balance")
@Entity
public class CompanyBalance {
	
	@Id
	private Integer id;
	
	@Column(name = "last_update_date")
	private Date lastUpdateDate;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Column(name = "balance")
	@Digits(fraction = 2, integer = 9)
	private Double Balance;

}
