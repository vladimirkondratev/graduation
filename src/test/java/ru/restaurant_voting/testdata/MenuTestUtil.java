package ru.restaurant_voting.testdata;

import ru.restaurant_voting.TestMatcher;
import ru.restaurant_voting.model.Menu;

import java.time.LocalDate;

public class MenuTestUtil {

    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingFieldsComparator(Menu.class, "restaurant", "dishes");

    public static final int MENU_1_ID = 100006;
    public static final int MENU_ID_WITH_NO_DISHES = 100010;
    public static final Menu MENU_1 = new Menu(MENU_1_ID, LocalDate.of(2020, 03, 25));
    public static final Menu MENU_2 = new Menu(100007, LocalDate.of(2020, 03, 24));
    public static final Menu MENU_3 = new Menu(100008, LocalDate.of(2020, 03, 23));
    public static final Menu MENU_4 = new Menu(100009, LocalDate.of(2020, 03, 25));
    public static final Menu MENU_5 = new Menu(MENU_ID_WITH_NO_DISHES, LocalDate.of(2020, 03, 24));

    public static Menu getNew() {
        return new Menu(null, LocalDate.of(2020, 03, 26));
    }

    public static Menu getUpdated() {
        Menu updated = new Menu(MENU_1.getId(), LocalDate.of(2020, 5, 7));
        return updated;
    }
}
