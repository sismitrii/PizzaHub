package fr.eni.pizzaHub.bll;

import java.sql.Time;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.pizzaHub.dal.TimeSlotRepository;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {
    private TimeSlotRepository timeSlotRepository;

    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    public List<Time> all() {
        return this.timeSlotRepository.all();
    }
}
