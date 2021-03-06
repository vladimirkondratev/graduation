package ru.restaurant_voting.testdata;

import ru.restaurant_voting.TestMatcher;
import ru.restaurant_voting.model.Dish;

public class DishTestUtil {

    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingFieldsComparator(Dish.class,"menu");

    public static final int DISH_1_ID = 100011;
    public static final Dish DISH_1 = new Dish(DISH_1_ID, "pizza_ollis_1", 45000);
    public static final Dish DISH_2 = new Dish(100012, "pizza_ollis_2", 60000);
    public static final Dish DISH_3 = new Dish(100013, "pizza_ollis_3", 80000);
    public static final Dish DISH_4 = new Dish(100014, "pizza_ollis_4", 30000);
    public static final Dish DISH_5 = new Dish(100015, "pizza_ollis_5", 25000);
    public static final Dish DISH_6 = new Dish(100016, "pizza_ollis_6", 48000);
    public static final Dish DISH_7 = new Dish(100017, "pizza_DoDo_1", 36000);
    public static final Dish DISH_8 = new Dish(100018, "pizza_DoDo_2", 50000);

    public static Dish getNew() {
        return new Dish(null, "new", 1000);
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(DISH_1);
        updated.setPrice(10);
        return updated;
    }
}
