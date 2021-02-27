/**
 * @Since Feb 27, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package com.nayeem.employee.management.service.impl
 */
package com.nayeem.employee.management.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nayeem.employee.management.common.exception.CustomDataIntegrityViolationException;
import com.nayeem.employee.management.common.exception.RecordNotFoundException;
import com.nayeem.employee.management.common.message.BaseResponse;
import com.nayeem.employee.management.common.message.CustomMessage;
import com.nayeem.employee.management.model.entity.CompanyBalance;
import com.nayeem.employee.management.repository.CompanyBalanceRepository;

/**
 * @author Nayeem
 *
 */

@Service
public class CompanyBalanceService implements com.nayeem.employee.management.service.CompanyBalanceService {

	@Autowired
	private CompanyBalanceRepository companyBalanceRepository; 
	
	@Override
	public BaseResponse addBalance(Double amount) {
		try {
			CompanyBalance companyBalance = companyBalanceRepository.findById(1).orElseThrow(() -> new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND));
			
			companyBalance.setBalance(companyBalance.getBalance() + amount);
			companyBalance.setLastUpdateDate(new Date());
			companyBalanceRepository.save(companyBalance);
			return new BaseResponse(CustomMessage.BALANCE_ADDED, HttpStatus.CREATED.value());
		}catch (Exception e) {
			throw new CustomDataIntegrityViolationException(CustomMessage.BALANCE_ADDED_FAILED);
		}
	}
	@Override
	public BaseResponse withdrowBalance(Double amount) {
		try {
			CompanyBalance companyBalance = companyBalanceRepository.findById(1).orElseThrow(() -> new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND));
			
			companyBalance.setBalance(companyBalance.getBalance() - amount);
			companyBalance.setLastUpdateDate(new Date());
			companyBalanceRepository.save(companyBalance);
			return new BaseResponse(CustomMessage.BALANCE_ADDED, HttpStatus.CREATED.value());
		}catch (Exception e) {
			throw new CustomDataIntegrityViolationException(CustomMessage.BALANCE_ADDED_FAILED);
		}
	}

	@Override
	public Double currentBalance() {
		CompanyBalance balance = companyBalanceRepository.findById(1).orElseThrow(() -> new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND));
		
		return balance.getBalance();
	}

}
