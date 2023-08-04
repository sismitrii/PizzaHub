package fr.eni.pizzaHub.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TimeSlotRepositoryImpl implements TimeSlotRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TimeSlotRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Time> all() {
        String query = String.join("\n", new String[] {
            "select",
            "  slot as slot",
            "from",
            "  TimeSlot",
            "where",
            "  reservedPizza < 5",
            "  and slot > cast(getdate() as time)"
        });
        return jdbcTemplate.query(query, new TimeRowMapper());
    }

    private static class TimeRowMapper implements RowMapper<Time> {
        @Override
        public Time mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getTime("slot");
        }
    }
}


        // private static class TimeRowMapper implements RowMapper<Time> {

        //     @Override
        
        //     public Time mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        //         Time time = rs.getTime("[order_id]");
        
        //         return time;