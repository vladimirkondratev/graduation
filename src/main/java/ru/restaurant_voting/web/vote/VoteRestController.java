package ru.restaurant_voting.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant_voting.AuthorizedUser;
import ru.restaurant_voting.model.Vote;
import ru.restaurant_voting.service.VoteService;

import java.net.URI;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/votes";

    private final VoteService voteService;

    public VoteRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    /**
     * Vote for a restaurant or change own vote to another restaurant (before deadline time is end)
     *
     * @param restaurantId - ID of restaurant
     * @param authUser     - auth user credentials
     * @return {@code 201 "Created"} and {@link Vote} entity if vote was successful,
     * * {@code 200 OK} if vote change was successful or
     * * {@code 422 "Unprocessable Entity"} if vote change was not successful
     */
    @PostMapping
    public ResponseEntity<Vote> vote(@RequestParam int restaurantId, @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("vote for restaurant: {}", restaurantId);
        Vote created = voteService.doVote(authUser.getId(), restaurantId);
        if (created != null) {
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
    }
}
