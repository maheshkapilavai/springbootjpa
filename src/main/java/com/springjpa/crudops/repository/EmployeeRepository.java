package com.springjpa.crudops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springjpa.crudops.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
