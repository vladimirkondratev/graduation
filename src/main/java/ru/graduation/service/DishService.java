package ru.graduation.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduation.model.Dish;
import ru.graduation.model.Menu;
import ru.graduation.repository.DishRepository;
import ru.graduation.repository.MenuRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFound;
import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class DishService {

    private final DishRepository dishRepository;

    private final MenuRepository menuRepository;

    public DishService(DishRepository dishRepository, MenuRepository menuRepository) {
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    @CacheEvict(value = "restaurants", allEntries = true)
    public Dish create(Dish dish, int menuId, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        Menu menu = checkNotFound(menuRepository.getForRestaurant(menuId, restaurantId), "menu not found");
        //Menu menu = checkNotFoundWithId(menuRepository.getOne(menuId), menuId);
        dish.setMenu(menu);
        return dishRepository.save(dish);
    }

    @Transactional
    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Dish dish, int menuId, int restaurantId) {
        Assert.notNull(dish, "meal must not be null");
        Menu menu = checkNotFound(menuRepository.getForRestaurant(menuId, restaurantId), "menu not found");
        dish.setMenu(menu);
        checkNotFoundWithId(dishRepository.save(dish), dish.getId());
    }

    @Transactional
    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int dishId, int menuId, int restaurantId) {
        checkNotFound(menuRepository.getForRestaurant(menuId, restaurantId), "menu not found");
        checkNotFoundWithId(dishRepository.delete(dishId, menuId) != 0, dishId);
    }

    public Dish get(int dishId, int menuId, int restaurantId) {
        checkNotFound(menuRepository.getForRestaurant(menuId, restaurantId), "menu not found");
        return checkNotFoundWithId(dishRepository.get(dishId, menuId), dishId);
    }

    public List<Dish> getAllForMenu(int menuId) {
        //Assert.notNull(menuId, "menu id must not be null");
        return dishRepository.getAllForMenu(menuId);
    }
}
