package ru.graduation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@NamedEntityGraph(
        name = "graph.restaurant.menus",
        attributeNodes = {
                @NamedAttributeNode(value = "menus", subgraph = "graph.restaurant.menus.dishes")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "graph.restaurant.menus.dishes",
                        attributeNodes = {@NamedAttributeNode("dishes")}
                )
        }
)
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @OrderBy("date DESC")
    @JsonManagedReference
    private Set<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant){
        this(restaurant.id, restaurant.name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
