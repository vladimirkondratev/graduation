package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.graduation.model.Dish;
import ru.graduation.util.exeption.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.TestData.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class DishServiceTest {

    @Autowired
    private DishService service;

    @Test
    void create() {
        Dish newDish = getNewDish();
        Dish created = service.create(new Dish(newDish), MENU_1_ID, RESTAURANT1_ID);
        int newId = created.getId();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
    }

    @Test
    void update() {
        Dish updated = getUpdatedDish();
        service.update(updated, MENU_1_ID, RESTAURANT1_ID);
        DISH_MATCHER.assertMatch(service.get(updated.getId(), MENU_1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    void delete() {
        service.delete(DISH_1_ID, MENU_1_ID, RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.get(DISH_1_ID, MENU_1_ID, RESTAURANT1_ID));
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1, MENU_1_ID, RESTAURANT1_ID));
    }

    @Test
    void get() {
        Dish dish = service.get(DISH_1_ID, MENU_1_ID, RESTAURANT1_ID);
        DISH_MATCHER.assertMatch(dish, DISH_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1, MENU_1_ID, RESTAURANT1_ID));
    }

    @Test
    void getAllForMenu() {
        List<Dish> dishes = service.getAllForMenu(MENU_1_ID);
        DISH_MATCHER.assertMatch(dishes, DISH_1, DISH_2);
    }
}