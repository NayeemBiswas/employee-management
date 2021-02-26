/**
 * @Since Feb 26, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package employee-management
 */
package com.nayeem.employee.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nayeem.employee.management.model.entity.Employee;

/**
 * @author Nayeem
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Employee findByEmail(String email);
	

}
