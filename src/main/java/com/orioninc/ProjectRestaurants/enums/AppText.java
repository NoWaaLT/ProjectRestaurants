package com.orioninc.ProjectRestaurants.enums;

import lombok.Getter;

@Getter
public enum AppText {
    DISH_BY_ID_NOT_FOUND("Dish not found by id:"),
    DISH_BY_MENU_ID_NOT_FOUND("Dish not found by menu id:"),

    PRODUCT_BY_ID_NOT_FOUND("Product not found by id: "),
    PRODUCT_IN_RESTAURANT_BY_ID_NOT_FOUND("Product in restaurant by id not found: "),
    PRODUCT_BY_NAME_AND_RESTAURANT_NOT_FOUND("Product not found by name: "),

    ACTIVE_ORDERS_EMPTY("There is no active orders."),

    PRODUCT_EXPIRE_BY_ID("Product expire not found by id: "),
    EXPIRES_EMPTY("There isn't any expires."),

    NO_ORDER_BY_USER("User don't have any orders : "),
    ORDERS_EMPTY("There isn't any orders at the moment."),

    RECIPE_BY_ID_NOT_FOUND("Recipe not found by id: "),
    RECIPES_EMPTY("There isn't any recipes."),

    RESTAURANT_BY_ID_NOT_FOUND("Restaurant not found by id: "),
    RESTAURANTS_EMPTY("There isn't any restaurants."),

    MENU_BY_ID_NOT_FOUND("Menu not found by id: "),
    MENUS_EMPTY("There isn't any menu."),
    MENUS_EMPTY_BY_RESTAURANT("There isn't any menu in this restaurant."),

    USER_BY_ID_NOT_FOUND("User not found by id: "),
    USER_BY_USERNAME_NOT_FOUND("User not found by username: "),
    USER_NO_ORDER("You don't have any active orders."),
    USERS_EMPTY("No users in the database");

    private final String description;
    AppText(String description) {
        this.description = description;
    }
}
