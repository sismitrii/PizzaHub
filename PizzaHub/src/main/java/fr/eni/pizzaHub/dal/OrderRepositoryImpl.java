package fr.eni.pizzaHub.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.aot.generate.InMemoryGeneratedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import fr.eni.pizzaHub.bo.OnSiteOrder;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
    }
	
	@Override
	public OnSiteOrder findOnSiteOrderByOrderId(int orderId) {
		String sql = "SELECT order_id, table_number, seats, order_step FROM RestaurantOrder ro WHERE ro.order_id = :orderId";
		
		SqlParameterSource parameters = new MapSqlParameterSource("orderId", orderId);
		
		return namedJdbcTemplate.queryForObject(sql, parameters,(rs, rowNum) -> new OnSiteOrder(rs.getInt("order_id"), rs.getInt("table_number"), rs.getInt("seats"), rs.getInt("order_step")));
		
	}

	@Override
	public OnSiteOrder findOnSiteOrderByTableNumber(int tableNumber) {
		String sql = "SELECT order_id, table_number, seats, order_step FROM RestaurantOrder ro WHERE ro.table_number = :tableNumber";
		
		try {
		SqlParameterSource parameters = new MapSqlParameterSource("tableNumber", tableNumber);
		
		return namedJdbcTemplate.queryForObject(sql, parameters,(rs, rowNum) -> new OnSiteOrder(rs.getInt("order_id"), rs.getInt("table_number"), rs.getInt("seats"), rs.getInt("order_step")));
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public void createRestaurantOrder(int tableNumber, int seatNumber) {
		String INSERT_ORDER = "INSERT INTO [Order](is_completed) VALUES (0)";
		String INSERT_RESTAURANT_ORDER = "INSERT INTO RestaurantOrder(order_id, table_number,seats) VALUES (:orderId, :tableNumber, :seatNumber)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
		    PreparedStatement ps = connection.prepareStatement(INSERT_ORDER, 
		                           Statement.RETURN_GENERATED_KEYS);
		    return ps;
		}, keyHolder);
		
		int orderId = keyHolder.getKey().intValue();
		
		SqlParameterSource parameters = new MapSqlParameterSource("orderId", orderId)
		.addValue("tableNumber", tableNumber)
		.addValue("seatNumber", seatNumber);
		
        namedJdbcTemplate.update(INSERT_RESTAURANT_ORDER, parameters);		
	}

	@Override
	public void updateSeatNumber(int tableNumber, int seatNumber) {
		String UPDATE_SEATS_NUMBER = "UPDATE RestaurantOrder "
				+ "SET seats = :seatNumber "
				+ "WHERE table_number = :tableNumber";
		
		SqlParameterSource parameters = new MapSqlParameterSource("seatNumber", seatNumber)
		.addValue("tableNumber", tableNumber);
		
        namedJdbcTemplate.update(UPDATE_SEATS_NUMBER, parameters);
	}
	
//	private static class OnSiteOrderRowMapper implements RowMapper<OnSiteOrder> {
//	    @Override
//	    public OnSiteOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
//	    	OnSiteOrder onSiteOrder = new OnSiteOrder(rs.getInt("[order_id]"), rs.getInt("[table_number]"), rs.getInt("[seats]"), rs.getInt("[order_step]"));
//	        return onSiteOrder;
//	    }
//	}

}
