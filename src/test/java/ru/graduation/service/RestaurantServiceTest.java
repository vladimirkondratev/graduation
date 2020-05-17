package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.graduation.testdata.RestaurantTestData;
import ru.graduation.model.Restaurant;
import ru.graduation.util.exeption.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    void create() {
        Restaurant newRestaurant = RestaurantTestData.getNewRestaurant();
        Restaurant created = service.create(newRestaurant);
        int newId = created.getId();
        newRestaurant.setId(newId);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    void update() {
        Restaurant updated = RestaurantTestData.getUpdatedRestaurant();
        service.update(new Restaurant(updated));
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(service.get(RestaurantTestData.RESTAURANT1_ID), updated);
    }

    @Test
    void delete() {
        service.delete(RestaurantTestData.RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.get(RestaurantTestData.RESTAURANT1_ID));
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1));
    }

    @Test
    void get() {
        Restaurant restaurant = service.get(RestaurantTestData.RESTAURANT1_ID);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(restaurant, RestaurantTestData.RESTAURANT_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    void getAll() {
        List<Restaurant> all = service.getAll();
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(all, RestaurantTestData.RESTAURANT_1, RestaurantTestData.RESTAURANT_2, RestaurantTestData.RESTAURANT_3);
    }

    @Test
    void getAllForDate() {
        List<Restaurant> all = service.getAllRestaurantWithMenuAndMealForDate(LocalDate.of(2020, 3, 25));
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(all, RestaurantTestData.RESTAURANT_2, RestaurantTestData.RESTAURANT_1);
    }
}