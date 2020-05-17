package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.graduation.MenuTestData;
import ru.graduation.RestaurantTestData;
import ru.graduation.model.Menu;
import ru.graduation.util.exeption.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class MenuServiceTest {

    @Autowired
    private MenuService service;

    @Test
    void create() {
        Menu newMenu = MenuTestData.getNewMenu();
        Menu created = service.create(new Menu(newMenu), RestaurantTestData.RESTAURANT1_ID);
        int newId = created.getId();
        newMenu.setId(newId);
        MenuTestData.MENU_MATCHER.assertMatch(created, newMenu);
    }

    @Test
    void update() {
        Menu updated = MenuTestData.getUpdatedMenu();
        service.update(updated, RestaurantTestData.RESTAURANT1_ID);
        MenuTestData.MENU_MATCHER.assertMatch(service.get(updated.getId(), RestaurantTestData.RESTAURANT1_ID), updated);
    }

    @Test
    void delete() {
        service.delete(MenuTestData.MENU_1_ID, RestaurantTestData.RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.get(MenuTestData.MENU_1.getId(), RestaurantTestData.RESTAURANT1_ID));
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1, RestaurantTestData.RESTAURANT1_ID));
    }

    @Test
    void get() {
        Menu menu = service.get(MenuTestData.MENU_1_ID, RestaurantTestData.RESTAURANT1_ID);
        MenuTestData.MENU_MATCHER.assertMatch(menu, MenuTestData.MENU_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1, RestaurantTestData.RESTAURANT1_ID));
    }

    @Test
    void getAllForRestaurant() {
        List<Menu> menus = service.getAllForRestaurant(RestaurantTestData.RESTAURANT1_ID);
        MenuTestData.MENU_MATCHER.assertMatch(menus, MenuTestData.MENU_1, MenuTestData.MENU_2, MenuTestData.MENU_3);
    }
}