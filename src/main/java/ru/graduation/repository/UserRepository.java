package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.User;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

}
