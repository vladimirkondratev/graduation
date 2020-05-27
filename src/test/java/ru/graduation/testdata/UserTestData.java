package ru.graduation.testdata;

import ru.graduation.TestMatcher;
import ru.graduation.model.Role;
import ru.graduation.model.User;

import java.util.Collections;
import java.util.Date;

public class UserTestData {
    public static final int SEQ = 100_000;

    public static final int ADMIN_ID = SEQ;
    public static final int USER_ID = SEQ + 1;

    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin@email.ru", "password", Role.ROLE_ADMIN, Role.ROLE_USER);
    public static final User USER = new User(USER_ID, "user", "user@email.ru", "password", Role.ROLE_USER);


    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator(User.class, "registered", "votes");


    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        return updated;
    }


}
