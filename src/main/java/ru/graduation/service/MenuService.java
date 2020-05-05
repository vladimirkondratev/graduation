package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Menu;
import ru.graduation.repository.MenuRepository;
import ru.graduation.repository.RestaurantRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFound;
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
        Assert.notNull(restaurantId, "restaurant must not be null");
        return menuRepository.save(menu, restaurantId);
    }

    public void update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        Assert.notNull(restaurantId, "restaurant id must not be null");
        checkNotFoundWithId(menuRepository.save(menu, restaurantId), menu.getId());
    }

    public int delete(int id, int restaurantId) {
        Assert.notNull(id, "menu id must not be null");
        Assert.notNull(id, "restaurant id must not be null");
        return checkNotFound(menuRepository.delete(id, restaurantId), "");
    }

    public Menu get(int id, int restaurantId) {
        Assert.notNull(id, "menu id must not be null");
        Assert.notNull(restaurantId, "restaurant id must not be null");
        return checkNotFoundWithId(menuRepository.get(id, restaurantId), id);
    }

    public List<Menu> getAll(int restaurantId) {
        Assert.notNull(restaurantId, "restaurant id must not be null");
        return menuRepository.getAll(restaurantId);
    }
}
