package fr.eni.pizzaHub.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.pizzaHub.bll.OnlineOrderService;
import fr.eni.pizzaHub.bo.OnlineOrder;
import fr.eni.pizzaHub.dto.OnlineOrderRequest;

@RestController
@RequestMapping("onlineOrder")
public class OnlineOrderController {
    private final OnlineOrderService onlineOrderService;

    public OnlineOrderController(OnlineOrderService onlineOrderService) {
        this.onlineOrderService = onlineOrderService;
    }

    @GetMapping(path="/debug", produces="Application/JSON")
    public String ez() {
    	return "{\"hello\":\"world\"}";
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody OnlineOrderRequest request) {
        if (onlineOrderService.createOrder(request)) {
            return new ResponseEntity<Object>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
    }
}