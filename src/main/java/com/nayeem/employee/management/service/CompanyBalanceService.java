/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package com.nayeem.employee.management.service
 */
package com.nayeem.employee.management.service;

import com.nayeem.employee.management.common.message.BaseResponse;

/**
 * @author Nayeem
 *
 */
public interface CompanyBalanceService {
	
	public BaseResponse addBalance(Double amount);
	
	public Double currentBalance();
	public BaseResponse withdrowBalance(Double amount);
	

}
