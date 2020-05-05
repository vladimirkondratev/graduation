package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Dish;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("")
        //TODO
    Dish save(Dish Dish, int menuId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:dishId AND d.menu.id=:menuId")
    int delete(@Param("dishId") int dishId, @Param("menuId") int menuId);

    @Query("SELECT Dish FROM Dish d WHERE d.id=:dishId AND d.menu.id=:menuId")
    Dish get(@Param("dishId") int dishId, @Param("menuId") int menuId);

    @Query("SELECT d FROM Dish d WHERE d.menu.id=:menuID")
    List<Dish> getAllForMenu(@Param("menuId") int menuId);
}
