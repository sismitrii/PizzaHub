package fr.eni.pizzaHub.bll;

import java.util.Comparator;

import fr.eni.pizzaHub.bo.OnlineOrder;

class OnlineOrderComparator implements Comparator<OnlineOrder> {
    @Override
    public int compare(OnlineOrder a, OnlineOrder b) {
        return a.getTimeSlot().compareTo(b.getTimeSlot());
    }
}
