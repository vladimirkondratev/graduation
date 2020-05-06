package ru.graduation;

import ru.graduation.model.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TestData {
    public static final int SEQ = 100_000;

    public static final int USER_ID = SEQ;
    public static final int ADMIN_ID = SEQ + 1;

    public static final User USER = new User(USER_ID, "user", "user@user.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin@admin.ru", "password", Role.ROLE_ADMIN);

    public static final Dish DISH_1 = new Dish(100010, "pizza_ollis_1", 45000);
    public static final Dish DISH_2 = new Dish(100011, "pizza_ollis_2", 60000);
    public static final Dish DISH_3 = new Dish(100012, "pizza_ollis_3", 80000);
    public static final Dish DISH_4 = new Dish(100013, "pizza_ollis_4", 30000);
    public static final Dish DISH_5 = new Dish(100014, "pizza_ollis_5", 25000);
    public static final Dish DISH_6 = new Dish(100015, "pizza_ollis_6", 48000);
    public static final Dish DISH_7 = new Dish(100016, "pizza_DoDo_1", 36000);
    public static final Dish DISH_8 = new Dish(100017, "pizza_DoDo_2", 50000);

    public static final Menu MENU_1 = new Menu(100005, LocalDate.of(2020, 03, 25), List.of(DISH_1, DISH_2));
    public static final Menu MENU_2 = new Menu(100006, LocalDate.of(2020, 03, 24), List.of(DISH_3, DISH_4, DISH_5));
    public static final Menu MENU_3 = new Menu(100007, LocalDate.of(2020, 03, 23), List.of(DISH_6));
    public static final Menu MENU_4 = new Menu(100008, LocalDate.of(2020, 03, 25), List.of(DISH_7, DISH_8));
    public static final Menu MENU_5 = new Menu(100009, LocalDate.of(2020, 03, 24), null);

    public static final Restaurant RESTAURANT_1 = new Restaurant(100002, "Ollis", List.of(MENU_1, MENU_2, MENU_3));
    public static final Restaurant RESTAURANT_2 = new Restaurant(100002, "DoDo", List.of(MENU_4, MENU_5));
    public static final Restaurant RESTAURANT_3 = new Restaurant(100003, "PizzaHut", null);

    public static final Vote VOTE_1 = new Vote(100018, LocalDate.of(2020,03,25), USER, RESTAURANT_1);
    public static final Vote VOTE_2 = new Vote(100019, LocalDate.of(2020,03,25), ADMIN, RESTAURANT_1);
    public static final Vote VOTE_3 = new Vote(100020, LocalDate.of(2020,03,24), USER, RESTAURANT_2);
    public static final Vote VOTE_4 = new Vote(100021, LocalDate.of(2020,03,24), ADMIN, RESTAURANT_1);
    public static final Vote VOTE_5 = new Vote(100022, LocalDate.of(2020,03,23), USER, RESTAURANT_2);
    public static final Vote VOTE_6 = new Vote(100023, LocalDate.of(2020,03,23), ADMIN, RESTAURANT_2);

    public static User getNewUser() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
    }

    public static User getUpdatedUser() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        return updated;
    }

    public static Dish getNewDish(){
        return new Dish(null, "new", 1000);
    }

    public static Dish getUpdatedDish(){
        Dish updated = new Dish(DISH_1);
        updated.setPrice(10);
        return updated;
    }

    public static Menu getNewMenu(){
        return new Menu(null, LocalDate.of(2020, 03, 26), null);
    }

    public static Menu getUpdatedMenu(){
        Menu updated = new Menu(MENU_1);
        updated.setDishes(List.of(getNewDish()));
        return updated;
    }

    public static Restaurant getNewRestaurant(){
        return new Restaurant(null, "new", null);
    }

    public static Restaurant getUpdatedRestaurant(){
        Restaurant updated = new Restaurant(RESTAURANT_1);
        updated.setName("updated");
        return updated;
    }
}
