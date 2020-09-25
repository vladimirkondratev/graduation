package ru.restaurant_voting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant_voting.model.Vote;
import ru.restaurant_voting.service.VoteService;
import ru.restaurant_voting.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant_voting.TestUtil.readFromJson;
import static ru.restaurant_voting.TestUtil.userHttpBasic;
import static ru.restaurant_voting.testdata.RestaurantTestUtil.RESTAURANT_1_ID;
import static ru.restaurant_voting.testdata.UserTestUtil.*;
import static ru.restaurant_voting.testdata.VoteTestUtil.VOTE_MATCHER;
import static ru.restaurant_voting.testdata.VoteTestUtil.getNew;
import static ru.restaurant_voting.web.vote.VoteRestController.REST_URL;

class VoteRestControllerTest extends AbstractControllerTest {

    @Autowired
    private VoteService voteService;

    @Test
    void vote() throws Exception{
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT_1_ID))
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isCreated());
        Vote created = readFromJson(action, Vote.class);
        Vote newVote = getNew();
        int newId = created.getId();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.getForUserAndDate(USER_ID, LocalDate.now()), newVote);
    }
}