package fr.eni.pizzaHub.dal;

import fr.eni.pizzaHub.bo.Employee;

public interface EmployeeRepository {
	public Employee findByUsername(String username);
}
