/**
 * @Since Feb 27, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package com.nayeem.employee.management.controller
 */
package com.nayeem.employee.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nayeem.employee.management.common.message.BaseResponse;
import com.nayeem.employee.management.service.impl.CompanyBalanceService;

/**
 * @author Nayeem
 *
 */

@RequestMapping("/company")
@RestController
@Validated
public class CompanyBalanceController {
	
	@Autowired
	private CompanyBalanceService companyBalanceService;
	
	@PostMapping(value = { "/add-balance/{amount}"})
	public ResponseEntity<BaseResponse> addBalance(@PathVariable("amount") Double amount) {
		BaseResponse response = companyBalanceService.addBalance(amount);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/balance")
	public Double companyBalance()
	{
		return companyBalanceService.currentBalance();
	}

}












