package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.graduation.model.Role;
import ru.graduation.model.User;
import ru.graduation.util.exeption.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.graduation.testdata.UserTestData.*;

class UserServiceTest extends AbstractServiceTest{

    @Autowired
    private UserService service;

    @Test
    void create() {
        User newUser = getNew();
        User created = service.create(new User(newUser));
        int newId = created.getId();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@email.ru", "newPass", Role.USER)));
    }

    @Test
    void update() throws Exception {
        User updated = getUpdated();
        service.update(new User(updated));
        USER_MATCHER.assertMatch(service.get(USER_ID), updated);
    }

    @Test
    void delete() {
        service.delete(USER2_ID_WITH_NO_VOTES);
        assertThrows(NotFoundException.class, () -> service.get(USER2_ID_WITH_NO_VOTES));
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1));
    }

    @Test
    void get() {
        User user = service.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    void getByEmail() throws Exception {
        User user = service.getByEmail("admin@email.ru");
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    void getAll() throws Exception {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, ADMIN, USER, USER2);
    }

    @Test
    void enable() {
        service.enable(USER_ID, false);
        assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        assertTrue(service.get(USER_ID).isEnabled());
    }
}