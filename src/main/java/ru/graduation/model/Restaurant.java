package ru.graduation.model;

import java.util.List;

public class Restaurant extends AbstractNamedEntity {

    private List<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, List<Menu> menus) {
        super(id, name);
        this.menus = menus;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "menus=" + menus +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
