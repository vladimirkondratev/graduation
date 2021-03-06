package ru.restaurant_voting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.service.RestaurantService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/restaurants";

    private final RestaurantService restaurantService;

    public UserRestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantService.getAllRestaurantWithMenuAndDishForDate(LocalDate.now());
        //return restaurantService.getAllRestaurantWithMenuAndMealForDate(LocalDate.of(2020, 3, 25));
    }
}
