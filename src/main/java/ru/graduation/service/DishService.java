package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;
import ru.graduation.repository.MenuRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFound;
import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final DishRepository dishRepository;

    private final MenuRepository menuRepository;

    public DishService(DishRepository dishRepository, MenuRepository menuRepository) {
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
    }

    public Dish create(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        Assert.notNull(menuId, "menu id must not be null");
        return dishRepository.save(dish, menuId);
    }

    public void update(Dish dish, int menuId) {
        Assert.notNull(dish, "meal must not be null");
        Assert.notNull(menuId, "menu id must not be null");
        checkNotFoundWithId(dishRepository.save(dish, menuId), dish.getId());
    }

    public int delete(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        Assert.notNull(menuId, "menu id must not be null");
        return checkNotFound(dishRepository.delete(dish.getId(), menuId), "");
    }

    public Dish get(int dishId, int menuId) {
        Assert.notNull(dishId, "dish id must not be null");
        Assert.notNull(menuId, "menu id must not be null");
        return checkNotFoundWithId(dishRepository.get(dishId, menuId), dishId);
    }

    public List<Dish> getAllForMenu(int menuId) {
        Assert.notNull(menuId, "menu id must not be null");
        return dishRepository.getAllForMenu(menuId);
    }
}
