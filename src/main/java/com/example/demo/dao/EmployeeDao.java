package com.example.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Employee;

@Repository
@Transactional
public class EmployeeDao {
	
	@Autowired
	private SessionFactory factory;
	
	public void saveEmployee(Employee employee) {
		getSession().save(employee);
	}
	
	public Employee updateEmployee(int id, Employee employee) {
		Employee emp = getSession().find(Employee.class, id);
				
		if(emp!=null) {
			emp.setDate(employee.getDate());
			emp.setName(employee.getName());
			emp.setDept(employee.getDept());
			saveEmployee(emp);
			return emp;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public List<Employee> getEmployees() {
		
	return getSession().createCriteria(Employee.class).list();
	}
	
	private Session getSession() {
		Session session= factory.getCurrentSession();
		if(session==null) {
			session = factory.openSession();
		}
		return session;
	}

}
