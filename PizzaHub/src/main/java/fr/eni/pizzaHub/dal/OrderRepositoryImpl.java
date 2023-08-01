package fr.eni.pizzaHub.dal;

import java.sql.Connection;
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

import fr.eni.pizzaHub.DALEXception;
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

		return namedJdbcTemplate.queryForObject(sql, parameters, (rs, rowNum) -> new OnSiteOrder(rs.getInt("order_id"),
				rs.getInt("table_number"), rs.getInt("seats"), rs.getInt("order_step")));

	}

	@Override
	public OnSiteOrder findOnSiteOrderByTableNumber(int tableNumber) {
		String sql = "SELECT order_id, table_number, seats, order_step FROM RestaurantOrder ro WHERE ro.table_number = :tableNumber";

		try {
			SqlParameterSource parameters = new MapSqlParameterSource("tableNumber", tableNumber);
			return namedJdbcTemplate.queryForObject(sql, parameters,
					(rs, rowNum) -> new OnSiteOrder(rs.getInt("order_id"), rs.getInt("table_number"),
							rs.getInt("seats"), rs.getInt("order_step")));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public void createRestaurantOrder(int tableNumber, int seatNumber) {
		String INSERT_ORDER = "INSERT INTO [Order](is_to_Prepare) VALUES (0)";
		String INSERT_RESTAURANT_ORDER = "INSERT INTO RestaurantOrder(order_id, table_number,seats) VALUES (:orderId, :tableNumber, :seatNumber)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
			return ps;
		}, keyHolder);

		int orderId = keyHolder.getKey().intValue();

		SqlParameterSource parameters = new MapSqlParameterSource("orderId", orderId)
				.addValue("tableNumber", tableNumber).addValue("seatNumber", seatNumber);

		namedJdbcTemplate.update(INSERT_RESTAURANT_ORDER, parameters);
	}

	@Override
	public void updateSeatNumber(int tableNumber, int seatNumber) {
		String UPDATE_SEATS_NUMBER = "UPDATE RestaurantOrder " + "SET seats = :seatNumber "
				+ "WHERE table_number = :tableNumber";

		SqlParameterSource parameters = new MapSqlParameterSource("seatNumber", seatNumber).addValue("tableNumber",
				tableNumber);

		namedJdbcTemplate.update(UPDATE_SEATS_NUMBER, parameters);
	}

	@Override
	public void addMenuItemToOrder(int orderId, int menuItemToAddId) {
		String INSERT_ORDER = "INSERT INTO MenuItem_Order(menu_item_id, order_id) VALUES (:menuItemToAddId,:orderId)";

		SqlParameterSource parameters = new MapSqlParameterSource("menuItemToAddId", menuItemToAddId)
				.addValue("orderId", orderId);

		namedJdbcTemplate.update(INSERT_ORDER, parameters);
	}

	@Override
	public void deleteOrder(int orderId) throws DALEXception {
		String DELETE_MENU_ITEM_ORDER = "DELETE FROM MenuItem_Order WHERE order_Id = ?";
		String DELETE_RESTAURANT_ORDER = "DELETE FROM RestaurantOrder WHERE order_Id = ?";
		String DELETE_ORDER = "DELETE FROM [Order] WHERE order_Id = ?";

		Connection connection;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);

			try {
				PreparedStatement pst = connection.prepareStatement(DELETE_MENU_ITEM_ORDER);
				pst.setInt(1, orderId);
				pst.execute();

				pst = connection.prepareStatement(DELETE_RESTAURANT_ORDER);
				pst.setInt(1, orderId);
				pst.execute();

				pst = connection.prepareStatement(DELETE_ORDER);
				pst.setInt(1, orderId);
				pst.execute();

				connection.commit();

			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
					throw new DALEXception("An error happen during rollback of deleteOrder", e1);
				}
				e.printStackTrace();
				throw new DALEXception("An error happen during deleteOrder", e);
			} finally {
				try {
					connection.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DALEXception("An error happen during setting back autoCommit in deleteOrder", e);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setOrderToBePrepared(int orderId) {
		String UPDATE_IS_TO_BE_PREPARED = "UPDATE [Order] SET is_to_Prepare = 1 WHERE order_id = :orderId";

		SqlParameterSource parameters = new MapSqlParameterSource("orderId", orderId);

		namedJdbcTemplate.update(UPDATE_IS_TO_BE_PREPARED, parameters);
	}

//	private static class OnSiteOrderRowMapper implements RowMapper<OnSiteOrder> {
//	    @Override
//	    public OnSiteOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
//	    	OnSiteOrder onSiteOrder = new OnSiteOrder(rs.getInt("[order_id]"), rs.getInt("[table_number]"), rs.getInt("[seats]"), rs.getInt("[order_step]"));
//	        return onSiteOrder;
//	    }
//	}

}
