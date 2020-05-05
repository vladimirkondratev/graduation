package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Menu;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Transactional
    @Modifying
    @Query("")
        //TODO
    Menu save(Menu menu, int restaurantId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:menuId AND m.restaurant.id=:restaurantId")
    int delete(@Param("menuId") int menuId, @Param("restaurantId") int restaurantId);

    @Query("SELECT Menu FROM Menu m WHERE m.id=:menuId AND m.restaurant.id=:restaurantId")
    Menu get(@Param("menuId") int menuId, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId")
    List<Menu> getAll(@Param("restaurantId") int restaurantId);
}
