package ru.restaurant_voting.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant_voting.model.Dish;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.service.DishService;
import ru.restaurant_voting.service.MenuService;
import ru.restaurant_voting.service.RestaurantService;
import ru.restaurant_voting.testdata.DishTestUtil;
import ru.restaurant_voting.testdata.MenuTestUtil;
import ru.restaurant_voting.testdata.RestaurantTestUtil;
import ru.restaurant_voting.util.exception.NotFoundException;
import ru.restaurant_voting.web.AbstractControllerTest;
import ru.restaurant_voting.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant_voting.TestUtil.readFromJson;
import static ru.restaurant_voting.TestUtil.userHttpBasic;
import static ru.restaurant_voting.testdata.DishTestUtil.DISH_1_ID;
import static ru.restaurant_voting.testdata.DishTestUtil.DISH_MATCHER;
import static ru.restaurant_voting.testdata.MenuTestUtil.*;
import static ru.restaurant_voting.testdata.RestaurantTestUtil.*;
import static ru.restaurant_voting.testdata.UserTestUtil.ADMIN;
import static ru.restaurant_voting.testdata.UserTestUtil.USER;

class AdminRestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantRestController.REST_URL + "/";
    private static final String MENUS_REST_URL = AdminRestaurantRestController.MENUS_REST_URL + "/";
    private static final String DISH_REST_URL = AdminRestaurantRestController.DISH_REST_URL + "/";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DishService dishService;

    @Test
    void createRestaurant() throws Exception {
        Restaurant newRestaurant = RestaurantTestUtil.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)))
                .andExpect(status().isCreated());
        Restaurant created = readFromJson(action, Restaurant.class);
        int newId = created.getId();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    void update() throws Exception {
        Restaurant updated = RestaurantTestUtil.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_1_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(RESTAURANT_1_ID), updated);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_3_ID_WITH_NO_MENU_AND_VOTES)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> restaurantService.get(RESTAURANT_3_ID_WITH_NO_MENU_AND_VOTES));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT_1));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3_WITH_NO_MENU));
    }

    @Test
    void createMenu() throws Exception {
        Menu newMenu = MenuTestUtil.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + MENUS_REST_URL, RESTAURANT_1_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu)))
                .andExpect(status().isCreated());
        Menu created = readFromJson(action, Menu.class);
        int newId = created.getId();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newId, RESTAURANT_1_ID), newMenu);
    }

    @Test
    void updateMenu() throws Exception {
        Menu updated = MenuTestUtil.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + MENUS_REST_URL + MENU_1_ID, RESTAURANT_1_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        MENU_MATCHER.assertMatch(menuService.get(MENU_1_ID, RESTAURANT_1_ID), updated);
    }

    @Test
    void deleteMenu() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MENUS_REST_URL + MENU_ID_WITH_NO_DISHES, RESTAURANT_2_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> menuService.get(MENU_ID_WITH_NO_DISHES, RESTAURANT_2_ID));
    }

    @Test
    void createDish() throws Exception {
        Dish newDish = DishTestUtil.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + DISH_REST_URL, RESTAURANT_1_ID, MENU_1_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andExpect(status().isCreated());
        Dish created = readFromJson(action, Dish.class);
        int newId = created.getId();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId, MENU_1_ID, RESTAURANT_1_ID), newDish);
    }

    @Test
    void updateDish() throws Exception {
        Dish updated = DishTestUtil.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + DISH_REST_URL + DISH_1_ID, RESTAURANT_1_ID, MENU_1_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        DISH_MATCHER.assertMatch(dishService.get(DISH_1_ID, MENU_1_ID, RESTAURANT_1_ID), updated);
    }

    @Test
    void deleteDish() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + DISH_REST_URL + DISH_1_ID, RESTAURANT_1_ID, MENU_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> dishService.get(DISH_1_ID, MENU_1_ID, RESTAURANT_1_ID));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}