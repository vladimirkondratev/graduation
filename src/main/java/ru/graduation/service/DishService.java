package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduation.model.Dish;
import ru.graduation.model.Menu;
import ru.graduation.repository.DishRepository;
import ru.graduation.repository.MenuRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final DishRepository dishRepository;

    private final MenuRepository menuRepository;

    public DishService(DishRepository dishRepository, MenuRepository menuRepository) {
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public Dish create(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
//        Assert.notNull(menuId, "menu id must not be null");
        Menu menu = checkNotFoundWithId(menuRepository.getOne(menuId), menuId);
        dish.setMenu(menu);
        return dishRepository.save(dish);
    }

    public void update(Dish dish, int menuId) {
        Assert.notNull(dish, "meal must not be null");
        //Assert.notNull(menuId, "menu id must not be null");
        dish.setMenu(menuRepository.getOne(menuId));
        checkNotFoundWithId(dishRepository.save(dish), dish.getId());
    }

    public void delete(int dishId, int menuId) {
   //     Assert.notNull(dish, "dish must not be null");
 //       Assert.notNull(menuId, "menu id must not be null");
        checkNotFoundWithId(dishRepository.delete(dishId, menuId) != 0, dishId);
    }

    public Dish get(int dishId, int menuId) {
//        Assert.notNull(dishId, "dish id must not be null");
//        Assert.notNull(menuId, "menu id must not be null");
        return checkNotFoundWithId(dishRepository.get(dishId, menuId), dishId);
    }

    public List<Dish> getAllForMenu(int menuId) {
        Assert.notNull(menuId, "menu id must not be null");
        return dishRepository.getAllForMenu(menuId);
    }
}
