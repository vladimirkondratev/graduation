package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Menu;
import ru.graduation.testdata.MenuTestData;
import ru.graduation.util.exeption.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.MenuTestData.*;
import static ru.graduation.testdata.RestaurantTestData.RESTAURANT_1_ID;
import static ru.graduation.testdata.RestaurantTestData.RESTAURANT_2_ID;

class MenuServiceTest extends AbstractServiceTest{

    @Autowired
    private MenuService service;

    @Test
    void create() {
        Menu newMenu = MenuTestData.getNew();
        Menu created = service.create(new Menu(newMenu), RESTAURANT_1_ID);
        int newId = created.getId();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
    }

    @Test
    void update() {
        Menu updated = MenuTestData.getUpdated();
        service.update(updated, RESTAURANT_1_ID);
        MENU_MATCHER.assertMatch(service.get(updated.getId(), RESTAURANT_1_ID), updated);
    }

    @Test
    void delete() {
        service.delete(MENU_ID_WITH_NO_DISHES, RESTAURANT_2_ID);
        assertThrows(NotFoundException.class, () -> service.get(MENU_ID_WITH_NO_DISHES, RESTAURANT_2_ID));
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1, RESTAURANT_1_ID));
    }

    @Test
    void get() {
        Menu menu = service.get(MENU_1_ID, RESTAURANT_1_ID);
        MENU_MATCHER.assertMatch(menu, MENU_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1, RESTAURANT_1_ID));
    }

    @Test
    void getAllForRestaurant() {
        List<Menu> menus = service.getAllForRestaurant(RESTAURANT_1_ID);
        MENU_MATCHER.assertMatch(menus, MENU_1, MENU_2, MENU_3);
    }
}