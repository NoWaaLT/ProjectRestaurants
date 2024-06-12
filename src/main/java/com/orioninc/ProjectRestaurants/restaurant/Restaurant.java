package com.orioninc.ProjectRestaurants.restaurant;

import com.orioninc.ProjectRestaurants.order.OrderProcessing;

public abstract class Restaurant implements OrderProcessing {
    public String restaurantName;

    public abstract void sendOrder();
}
