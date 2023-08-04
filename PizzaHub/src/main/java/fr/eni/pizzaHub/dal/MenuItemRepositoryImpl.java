package fr.eni.pizzaHub.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;
import fr.eni.pizzaHub.bo.Pizza;
import fr.eni.pizzaHub.bo.Size;

@Repository
public class MenuItemRepositoryImpl implements MenuItemRepository{

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public MenuItemRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
    }
    
//	@Override
//	public List<MenuItem> getAll() {
//		String sql = "SELECT mi.menu_item_id, mi.name AS Name, mic.name AS MenuItemCategory, [price], p.size AS Size"
//				+ " FROM MenuItem mi INNER JOIN [MenuItemCategory] mic ON mi.[category_id] = mic.[id]"
//				+ " LEFT JOIN Pizza p ON mi.menu_item_id = p.menu_item_id ";
//   	 	
//        return jdbcTemplate.query(sql, new MenuItemRowMapper());
//	}
	
	@Override
	public List<MenuItem> findAllItemForCategory(MenuItemCategory menuItemCategory) {
		String sql = "SELECT mi.menu_item_id, mi.name AS Name, mic.name AS MenuItemCategory, price, mi.size AS Size"
				+ " FROM MenuItem mi"
				+ " INNER JOIN [MenuItemCategory] mic ON mi.[category_id] = mic.[id]"
				+ " WHERE mic.name = :menuItemCategory AND (mic.name != 'PIZZA' OR mi.size = 'L')";
		
		SqlParameterSource parameters = new MapSqlParameterSource("menuItemCategory", menuItemCategory.toString());
        return namedJdbcTemplate.query(sql, parameters, new MenuItemRowMapper());
        //to test
	}
	
	@Override
	public List<MenuItem> findMenuItemByOrderId(int orderId) {
		String sql = "SELECT mi.menu_item_id, mi.name AS Name, mic.name AS MenuItemCategory, price, size AS Size FROM MenuItem mi "
				+ "INNER JOIN MenuItemCategory mic ON mi.category_id = mic.id "
				+ "INNER JOIN MenuItem_Order mio ON mi.menu_item_id = mio.menu_item_id "
				+ "WHERE mio.order_Id = :orderId";
		
		SqlParameterSource parameters = new MapSqlParameterSource("orderId", orderId);
        return namedJdbcTemplate.query(sql, parameters, new MenuItemRowMapper());
	}
	
	private static class MenuItemRowMapper implements RowMapper<MenuItem> {
	    @Override
	    public MenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	MenuItem menuItem;
	    	if (rs.getString("Size") != null) {
	    		menuItem = new Pizza(rs.getInt("menu_item_id"), rs.getString("Name"), MenuItemCategory.valueOf(rs.getString("MenuItemCategory")), rs.getDouble("price"), Size.valueOf(rs.getString("size")));
	    	} else {
		        menuItem = new MenuItem(rs.getInt("menu_item_id"), rs.getString("Name"), MenuItemCategory.valueOf(rs.getString("MenuItemCategory")), rs.getDouble("price"));
	    	}
	        return menuItem;
	    }
	}
}


