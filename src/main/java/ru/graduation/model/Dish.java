package ru.graduation.model;

import java.time.LocalDate;

public class Dish extends AbstractNamedEntity {

    private double price;

    private LocalDate date;

    public Dish() {
    }

    public Dish(Integer id, String name, double price, LocalDate date) {
        super(id, name);
        this.price = price;
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
