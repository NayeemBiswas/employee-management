/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package com.nayeem.employee.management.common.message
 */
package com.nayeem.employee.management.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nayeemul
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
	
	private String messsage;
	private int code;

}
