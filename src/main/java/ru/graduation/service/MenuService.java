package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Menu;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.MenuRepository;
import ru.graduation.repository.RestaurantRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Menu create(Menu menu, Integer restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.getOne(restaurantId), restaurantId);
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

    public void delete(int id) {
        Assert.notNull(id, "menu id must not be null");
        //checkNotFoundWithId(repository.deleteById(id), id);
    }

    public Menu get(int id) {
        Assert.notNull(id, "menu id must not be null");
        return checkNotFoundWithId(menuRepository.findById(id).orElse(null), id);
    }

    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    public void update(Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        checkNotFoundWithId(menuRepository.save(menu), menu.getId());
    }
}
