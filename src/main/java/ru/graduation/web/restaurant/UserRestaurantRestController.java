package ru.graduation.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.graduation.model.Restaurant;
import ru.graduation.service.RestaurantService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/user/restaurants";

    private final RestaurantService restaurantService;

    public UserRestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantService.getAllRestaurantWithMenuAndMealForDate(LocalDate.now());
        //return restaurantService.getAllRestaurantWithMenuAndMealForDate(LocalDate.of(2020, 3, 25));
    }
}
