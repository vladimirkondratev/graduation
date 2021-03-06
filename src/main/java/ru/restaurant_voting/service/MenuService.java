package ru.restaurant_voting.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;

import java.util.List;

import static ru.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    @CacheEvict(value = "restaurants", allEntries = true)
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.getOne(restaurantId), restaurantId);
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

    @Transactional
    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        checkNotFoundWithId(menuRepository.save(menu), menu.getId());
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int menuId, int restaurantId) {
        checkNotFoundWithId(menuRepository.delete(menuId, restaurantId) != 0, menuId);
    }

    public Menu get(int menuId, int restaurantId) {
        return checkNotFoundWithId(menuRepository.get(menuId, restaurantId), menuId);
    }

    public List<Menu> getAllForRestaurant(int restaurantId) {
        return menuRepository.getAll(restaurantId);
    }
}
