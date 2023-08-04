package fr.eni.pizzaHub.controller;

import java.sql.Time;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.pizzaHub.bll.TimeSlotService;

@RestController
@RequestMapping("timeSlot")
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @GetMapping("/")
    public List<Time> all() {
        return this.timeSlotService.all();
    }
}
