package fr.eni.pizzaHub.dal;

import java.sql.Time;
import java.util.List;

public interface TimeSlotRepository {
    public List<Time> all();
}
