package fr.eni.pizzaHub.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;

@Repository

public class MenuItemRepositoryImpl implements MenuItemRepository{

	private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MenuItemRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
	@Override
	public List<MenuItem> getAll() {
		String sql = "SELECT [menu_item_id], mi.name AS Name, mic.name AS MenuItemCategory, [price] FROM MenuItem mi INNER JOIN [MenuItemCategory] mic ON mi.[category_id] = mic.[id]";
   	 	
        return jdbcTemplate.query(sql, new MenuItemRowMapper());
	
	}
	
	@Override
	public List<MenuItem> findAllItemForCategory(MenuItemCategory menuItemCategory) {
		String sql = "SELECT menuItemId, name, menuItemsCategory, price FROM MenuItems WHERE menuItemCategory = ?";
		
		return null;
		//to test
        //return jdbcTemplate.query(sql,menuItemCategory.toString(), new MenuItemRowMapper());
	}
	
	@Override
	public List<MenuItem> findMenuItemsByOrderId(int orderId) {
		return null;
		// TODO Auto-generated method stub
	}
	
	//weird to add a class into a class..
	private static class MenuItemRowMapper implements RowMapper<MenuItem> {
	    @Override
	    public MenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
	        MenuItem menuItem = new MenuItem(rs.getInt("menu_item_id"), rs.getString("Name"), MenuItemCategory.valueOf(rs.getString("MenuItemCategory")), rs.getDouble("price"));
	        return menuItem;
	    }
	}



}


