package ru.graduation.testdata;

import ru.graduation.TestMatcher;
import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.graduation.model.Vote.deadLine;
import static ru.graduation.testdata.RestaurantTestUtil.RESTAURANT_1;
import static ru.graduation.testdata.RestaurantTestUtil.RESTAURANT_2;
import static ru.graduation.testdata.UserTestUtil.ADMIN;
import static ru.graduation.testdata.UserTestUtil.USER;

public class VoteTestUtil {

    public static LocalDate voteDate = LocalDate.of(2020, 3, 25);
    public static LocalTime voteTimeBefore = deadLine.minusHours(2);
    public static LocalTime voteTimeAfter = deadLine.plusHours(2);

    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsComparator(Vote.class, "user", "restaurant");

    public static final Vote VOTE_1 = new Vote(100019, LocalDate.of(2020, 03, 25), ADMIN, RESTAURANT_1);
    public static final Vote VOTE_2 = new Vote(100020, LocalDate.of(2020, 03, 25), USER, RESTAURANT_1);
    public static final Vote VOTE_3 = new Vote(100021, LocalDate.of(2020, 03, 24), ADMIN, RESTAURANT_2);
    public static final Vote VOTE_4 = new Vote(100022, LocalDate.of(2020, 03, 24), USER, RESTAURANT_1);
    public static final Vote VOTE_5 = new Vote(100023, LocalDate.of(2020, 03, 23), ADMIN, RESTAURANT_2);
    public static final Vote VOTE_6 = new Vote(100024, LocalDate.of(2020, 03, 23), USER, RESTAURANT_2);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now(), ADMIN, RESTAURANT_1);
    }
    public static Vote getUpdated() {
        return new Vote(null, LocalDate.now(), USER, RESTAURANT_2);
    }
}
