package ru.restaurant_voting.testdata;

import ru.restaurant_voting.TestMatcher;
import ru.restaurant_voting.model.Restaurant;

public class RestaurantTestUtil {
    public static final int RESTAURANT_1_ID = 100003;
    public static final int RESTAURANT_2_ID = 100004;
    public static final int RESTAURANT_3_ID_WITH_NO_MENU_AND_VOTES = 100005;
    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_1_ID, "Ollis");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_2_ID, "DoDo");
    public static final Restaurant RESTAURANT_3_WITH_NO_MENU = new Restaurant(RESTAURANT_3_ID_WITH_NO_MENU_AND_VOTES, "PizzaHut");
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
