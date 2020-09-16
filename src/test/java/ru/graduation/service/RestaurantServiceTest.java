package ru.graduation.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.graduation.model.Restaurant;
import ru.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.RestaurantTestUtil.*;

class RestaurantServiceTest extends AbstractServiceTest{

    @Autowired
    private RestaurantService service;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
    }

    @Test
    void create() {
        Restaurant newRestaurant = getNew();
        Restaurant created = service.create(newRestaurant);
        int newId = created.getId();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    void update() {
        Restaurant updated = getUpdated();
        service.update(new Restaurant(updated));
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT_1_ID), updated);
    }

    @Test
    void delete() {
        service.delete(RESTAURANT_3_ID_WITH_NO_MENU_AND_VOTES);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT_3_ID_WITH_NO_MENU_AND_VOTES));
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1));
    }

    @Test
    void get() {
        Restaurant restaurant = service.get(RESTAURANT_1_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, RESTAURANT_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    void getAll() {
        List<Restaurant> all = service.getAll();
        RESTAURANT_MATCHER.assertMatch(all, RESTAURANT_1, RESTAURANT_2, RESTAURANT_3_WITH_NO_MENU);
    }

    @Test
    void getAllForDate() {
        List<Restaurant> all = service.getAllRestaurantWithMenuAndDishForDate(LocalDate.of(2020, 3, 25));
        RESTAURANT_MATCHER.assertMatch(all, RESTAURANT_2, RESTAURANT_1);
    }
}