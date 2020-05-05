package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Vote;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;
import ru.graduation.repository.VoteRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFound;
import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository  voteRepository;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository,
                       UserRepository userRepository,
                       RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Vote create(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        Assert.notNull(userId, "user id must not be null");
        return voteRepository.save(vote, userId);
    }

    public void update(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        Assert.notNull(userId, "user id must not be null");
        checkNotFoundWithId(voteRepository.save(vote, userId), vote.getId());
    }

    public int delete(int voteId, int userId) {
        Assert.notNull(voteId, "vote id must not be null");
        Assert.notNull(userId, "user id must not be null");
        return checkNotFound(voteRepository.delete(voteId, userId), "");
    }

    public Vote get(int voteId, int userId) {
        Assert.notNull(voteId, "vote id must not be null");
        Assert.notNull(userId, "user id must not be null");
        return checkNotFoundWithId(voteRepository.get(voteId, userId), voteId);
    }

    public List<Vote> getAll(int userId) {
        return voteRepository.getAll(userId);
    }
}
