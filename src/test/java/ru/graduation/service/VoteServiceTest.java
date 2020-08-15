package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.testdata.RestaurantTestUtil.RESTAURANT_1_ID;
import static ru.graduation.testdata.RestaurantTestUtil.RESTAURANT_2_ID;
import static ru.graduation.testdata.UserTestUtil.ADMIN_ID;
import static ru.graduation.testdata.UserTestUtil.USER_ID;
import static ru.graduation.testdata.VoteTestUtil.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void doNewVote() {
        service.setClockAndTimeZone(LocalDateTime.now());
        Vote newVote = getNew();
        Vote createdVote = service.doVote(ADMIN_ID, RESTAURANT_1_ID);
        int id = createdVote.getId();
        newVote.setId(id);
        VOTE_MATCHER.assertMatch(createdVote, newVote);
        VOTE_MATCHER.assertMatch(service.getForUserAndDate(ADMIN_ID, LocalDate.now()), newVote);
    }

    @Test
    void updateVoteBeforeDeadLine() {
        service.setClockAndTimeZone(LocalDateTime.of(voteDate, voteTimeBefore));
        Vote newVote = service.doVote(USER_ID, RESTAURANT_2_ID);
        VOTE_MATCHER.assertMatch(newVote, service.getForUserAndDate(USER_ID, voteDate));
    }

    @Test
    void updateVoteAfterDeadLine() {
        service.setClockAndTimeZone(LocalDateTime.of(voteDate, voteTimeAfter));
        Vote newVote = service.doVote(USER_ID, RESTAURANT_2_ID);
        assertThat(newVote).isNull();
        VOTE_MATCHER.assertMatch(VOTE_2, service.getForUserAndDate(USER_ID, voteDate));
    }

    @Test
    void getAllForDate() {
        List<Vote> votes = service.getAllForDate(voteDate);
        VOTE_MATCHER.assertMatch(votes, VOTE_1, VOTE_2);
    }
}