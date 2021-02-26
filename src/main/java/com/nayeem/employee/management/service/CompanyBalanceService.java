/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package employee-management
 */
package com.nayeem.employee.management.service;

import com.nayeem.employee.management.common.message.BaseResponse;

/**
 * @author Nayeem
 *
 */
public interface CompanyBalanceService {
	
	public BaseResponse addBalance(Double amount);
	

}
