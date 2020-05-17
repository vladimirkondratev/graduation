package ru.graduation.testdata;

import ru.graduation.TestMatcher;
import ru.graduation.model.Vote;

import java.time.LocalDate;

public class VoteTestData {

    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsComparator(Vote.class, "registered", "votes");

    public static final Vote VOTE_1 = new Vote(100018, LocalDate.of(2020, 03, 25), UserTestData.USER, RestaurantTestData.RESTAURANT_1);
    public static final Vote VOTE_2 = new Vote(100019, LocalDate.of(2020, 03, 25), UserTestData.ADMIN, RestaurantTestData.RESTAURANT_1);
    public static final Vote VOTE_3 = new Vote(100020, LocalDate.of(2020, 03, 24), UserTestData.USER, RestaurantTestData.RESTAURANT_2);
    public static final Vote VOTE_4 = new Vote(100021, LocalDate.of(2020, 03, 24), UserTestData.ADMIN, RestaurantTestData.RESTAURANT_1);
    public static final Vote VOTE_5 = new Vote(100022, LocalDate.of(2020, 03, 23), UserTestData.USER, RestaurantTestData.RESTAURANT_2);
    public static final Vote VOTE_6 = new Vote(100023, LocalDate.of(2020, 03, 23), UserTestData.ADMIN, RestaurantTestData.RESTAURANT_2);

}
