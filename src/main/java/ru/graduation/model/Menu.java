package ru.graduation.model;

import java.time.LocalDate;
import java.util.List;

public class Menu extends AbstractBaseEntity{
    private LocalDate date;

    private List<Dish> dishes;

    public Menu() {
    }

    public Menu(Integer id, LocalDate date, List<Dish> dishes) {
        super(id);
        this.date = date;
        this.dishes = dishes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "date=" + date +
                ", dishes=" + dishes +
                ", id=" + id +
                '}';
    }
}
