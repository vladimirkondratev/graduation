package ru.graduation.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Dish;
import ru.graduation.model.Menu;
import ru.graduation.model.Restaurant;
import ru.graduation.service.DishService;
import ru.graduation.service.MenuService;
import ru.graduation.service.RestaurantService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/rest/admin/restaurants";

    public static final String MENUS_REST_URL = "/{restaurantId}/menus";

    public static final String DISH_REST_URL = MENUS_REST_URL + "/{menuId}/dishes";

    private final RestaurantService restaurantService;

    private final MenuService menuService;

    private final DishService dishService;

    public AdminRestaurantRestController(RestaurantService restaurantService, MenuService menuService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.menuService = menuService;
        this.dishService = dishService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createRestaurant(@Valid @RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        Restaurant created = restaurantService.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update {}", restaurant);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        restaurantService.delete(id);
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get {}", id);
        return restaurantService.get(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantService.getAll();
    }

    @PostMapping(value = MENUS_REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenu(@Valid @RequestBody Menu menu, @PathVariable int restaurantId) {
        log.info("create menu {} for restaurant {}", menu, restaurantId);
        checkNew(menu);
        Menu created = menuService.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + MENUS_REST_URL + "/{menuId}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = MENUS_REST_URL + "/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMenu(@Valid @RequestBody Menu menu, @PathVariable int menuId, @PathVariable int restaurantId) {
        log.info("update menu {} for restaurant {}", menu, restaurantId);
        assureIdConsistent(menu, menuId);
        menuService.update(menu, restaurantId);
    }

    @DeleteMapping(value = MENUS_REST_URL + "/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenu(@PathVariable int restaurantId, @PathVariable int menuId) {
        log.info("delete menu {} for restaurant {}", menuId, restaurantId);
        menuService.delete(menuId, restaurantId);
    }

    @PostMapping(value = DISH_REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createDish(@Valid @RequestBody Dish dish,
                                           @PathVariable int restaurantId,
                                           @PathVariable int menuId) {
        log.info("create dish {} for menu {} for restaurant {}", dish, menuId, restaurantId);
        checkNew(dish);
        Dish created = dishService.create(dish, menuId, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + DISH_REST_URL + "/{dishId}")
                .buildAndExpand(restaurantId, menuId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = DISH_REST_URL + "/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDish(@Valid @RequestBody Dish dish,
                           @PathVariable int restaurantId,
                           @PathVariable int menuId,
                           @PathVariable int dishId) {
        log.info("update dish {} for menu {} for restaurant {}", dish, menuId, restaurantId);
        assureIdConsistent(dish, dishId);
        dishService.update(dish, menuId, restaurantId);
    }

    @DeleteMapping(value = DISH_REST_URL + "/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable int restaurantId,
                           @PathVariable int menuId,
                           @PathVariable int dishId) {
        log.info("delete dish {} for menu {} for restaurant {}", dishId, menuId, restaurantId);
        dishService.delete(dishId, menuId, restaurantId);
    }
}
