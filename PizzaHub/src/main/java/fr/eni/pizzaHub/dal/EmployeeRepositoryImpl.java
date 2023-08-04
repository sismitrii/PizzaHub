package fr.eni.pizzaHub.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import fr.eni.pizzaHub.bo.Employee;
import fr.eni.pizzaHub.bo.OnSiteOrder;
import fr.eni.pizzaHub.bo.Role;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
    }
    
	@Override
	public Employee findByUsername(String username) {
		String sql = "SELECT first_name, last_name, password, email, puuid, r.name AS role"
				+ " FROM Employee e"
				+ " INNER JOIN [Role] r ON e.[role_id] = r.id"
				+ " WHERE e.puuid = :username";
		
		SqlParameterSource parameters = new MapSqlParameterSource("username", username);
        return namedJdbcTemplate.queryForObject(sql, parameters, (rs, rowNum) -> new Employee(rs.getString("first_name"),
        		rs.getString("last_name"),
        		rs.getString("password"),
        		rs.getString("email"),
        		rs.getString("puuid"), 
        		Role.valueOf(rs.getString("role"))));
	}

}
