package com.nayeem.employee.management;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nayeem.employee.management.model.entity.CompanyBalance;
import com.nayeem.employee.management.repository.CompanyBalanceRepository;

@SpringBootApplication
public class EmployeeManagementApplication implements CommandLineRunner{
	@Autowired
	private CompanyBalanceRepository balanceRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		CompanyBalance companyBalance = new CompanyBalance(1, new Date(), (double) 0);
		balanceRepository.save(companyBalance);
	}

}
