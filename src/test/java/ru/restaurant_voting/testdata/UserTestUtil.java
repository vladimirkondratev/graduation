package ru.restaurant_voting.testdata;

import ru.restaurant_voting.TestMatcher;
import ru.restaurant_voting.model.Role;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;

public class UserTestUtil {
    public static final int SEQ = 100_000;

    public static final int ADMIN_ID = SEQ;
    public static final int USER_ID = SEQ + 1;
    public static final int USER2_ID_WITH_NO_VOTES = SEQ + 2;

    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin@email.ru", "password", Role.ADMIN, Role.USER);
    public static final User USER = new User(USER_ID, "user", "user@email.ru", "password", Role.USER);
    public static final User USER2 = new User(USER2_ID_WITH_NO_VOTES, "user2", "user2@email.ru", "password", Role.USER);


    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator(User.class, "registered", "votes", "password");


    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }

}
