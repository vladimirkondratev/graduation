package ru.graduation;

import ru.graduation.model.Menu;

import java.time.LocalDate;

public class MenuTestData {
    public static final int MENU_1_ID = 100005;
    public static final Menu MENU_1 = new Menu(MENU_1_ID, LocalDate.of(2020, 03, 25));
    public static final Menu MENU_2 = new Menu(100006, LocalDate.of(2020, 03, 24));
    public static final Menu MENU_3 = new Menu(100007, LocalDate.of(2020, 03, 23));
    public static final Menu MENU_4 = new Menu(100008, LocalDate.of(2020, 03, 25));
    public static final Menu MENU_5 = new Menu(100009, LocalDate.of(2020, 03, 24));
    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingFieldsComparator("restaurant", "dishes");

    public static Menu getNewMenu() {
        return new Menu(null, LocalDate.of(2020, 03, 26));
    }

    public static Menu getUpdatedMenu() {
        Menu updated = new Menu(MENU_1.getId(), LocalDate.of(2020, 5, 7));
        return updated;
    }
}
