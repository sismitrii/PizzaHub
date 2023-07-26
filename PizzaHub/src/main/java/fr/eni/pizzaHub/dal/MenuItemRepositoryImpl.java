//package fr.eni.pizzaHub.dal;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementSetter;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import fr.eni.pizzaHub.bo.MenuItem;
//import fr.eni.pizzaHub.bo.MenuItemCategory;
//
//@Repository
//
//public class MenuItemRepositoryImpl implements MenuItemRepository{
//
//	private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public MenuItemRepositoryImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//    
//	@Override
//	public List<MenuItem> getAll() {
//		String sql = "SELECT menuItemId, name, menuItemsCategory, price FROM MenuItems WHERE price >= ?";
//   	 	
//        return jdbcTemplate.query(sql, new MenuItemRowMapper());
//	
//	}
//	
//	//weird to add a class into a class..
//	private static class MenuItemRowMapper implements RowMapper<MenuItem> {
//	    @Override
//	    public MenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
//	        MenuItem menuItem = new MenuItem(rs.getInt("menuItemIId"), rs.getString("name"), MenuItemCategory.valueOf(rs.getString("menuItemCategory")), rs.getInt("price"));
//	        return menuItem;
//	    }
//	}
//}
//
//
