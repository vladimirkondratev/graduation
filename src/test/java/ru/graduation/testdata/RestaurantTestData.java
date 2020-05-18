package ru.graduation.testdata;

import ru.graduation.TestMatcher;
import ru.graduation.model.Restaurant;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = 100002;
    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT1_ID, "Ollis");
    public static final Restaurant RESTAURANT_2 = new Restaurant(100003, "DoDo");
    public static final Restaurant RESTAURANT_3 = new Restaurant(100004, "PizzaHut");
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsComparator(Restaurant.class, "menus");

    public static Restaurant getNew() {
        return new Restaurant(null, "new");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(RESTAURANT_1);
        updated.setName("updated");
        return updated;
    }
}
