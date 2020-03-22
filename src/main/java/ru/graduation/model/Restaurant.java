package main.java.ru.graduation.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Restaurant extends AbstractNamedEntity {

    private Map<LocalDate, List<Dish>> menu;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, Map<LocalDate, List<Dish>> menu) {
        super(id, name);
        this.menu = menu;
    }

    public Map<LocalDate, List<Dish>> getMenu() {
        return menu;
    }

    public void setMenu(Map<LocalDate, List<Dish>> menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "menu=" + menu +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
