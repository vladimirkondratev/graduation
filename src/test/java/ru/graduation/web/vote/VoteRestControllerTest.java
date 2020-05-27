package ru.graduation.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.graduation.model.Vote;
import ru.graduation.service.VoteService;
import ru.graduation.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.TestUtil.readFromJson;
import static ru.graduation.TestUtil.userHttpBasic;
import static ru.graduation.testdata.RestaurantTestData.RESTAURANT_1_ID;
import static ru.graduation.testdata.UserTestData.*;
import static ru.graduation.testdata.VoteTestData.VOTE_MATCHER;
import static ru.graduation.testdata.VoteTestData.getNew;
import static ru.graduation.web.vote.VoteRestController.REST_URL;

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

//    @Test
//    void getAllForDate() throws Exception{
//
//    }
}