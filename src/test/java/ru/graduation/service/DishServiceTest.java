package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Dish;
import ru.graduation.testdata.DishTestData;
import ru.graduation.util.exeption.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.DishTestData.*;
import static ru.graduation.testdata.MenuTestData.MENU_1_ID;
import static ru.graduation.testdata.RestaurantTestData.RESTAURANT_1_ID;

class DishServiceTest extends AbstractServiceTest{

    @Autowired
    private DishService service;

    @Test
    void create() {
        Dish newDish = DishTestData.getNew();
        Dish created = service.create(new Dish(newDish), MENU_1_ID, RESTAURANT_1_ID);
        int newId = created.getId();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
    }

    @Test
    void update() {
        Dish updated = getUpdated();
        service.update(updated, MENU_1_ID, RESTAURANT_1_ID);
        DISH_MATCHER.assertMatch(service.get(updated.getId(), MENU_1_ID, RESTAURANT_1_ID), updated);
    }

    @Test
    void delete() {
        service.delete(DISH_1_ID, MENU_1_ID, RESTAURANT_1_ID);
        assertThrows(NotFoundException.class, () -> service.get(DISH_1_ID, MENU_1_ID, RESTAURANT_1_ID));
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1, MENU_1_ID, RESTAURANT_1_ID));
    }

    @Test
    void get() {
        Dish dish = service.get(DISH_1_ID, MENU_1_ID, RESTAURANT_1_ID);
        DISH_MATCHER.assertMatch(dish, DISH_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1, MENU_1_ID, RESTAURANT_1_ID));
    }

    @Test
    void getAllForMenu() {
        List<Dish> dishes = service.getAllForMenu(MENU_1_ID);
        DISH_MATCHER.assertMatch(dishes, DISH_1, DISH_2);
    }
}