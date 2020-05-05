package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.model.Vote;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;
import ru.graduation.repository.VoteRepository;

import java.util.List;

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

    public Vote create(Vote vote, Integer restaurantId, Integer userId) {
        Assert.notNull(vote, "vote must not be null");
        Assert.notNull(restaurantId, "restaurantId must not be null");
        Assert.notNull(userId, "userId must not be null");
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.getOne(restaurantId), restaurantId);
        User user = checkNotFoundWithId(userRepository.getOne(userId), userId);
        vote.setRestaurant(restaurant);
        vote.setUser(user);
        return voteRepository.save(vote);
    }

    public void delete(int id) {
        Assert.notNull(id, "vote id must not be null");
        //checkNotFoundWithId(repository.deleteById(id), id);
    }

    public Vote get(int id) {
        Assert.notNull(id, "vote id must not be null");
        return checkNotFoundWithId(voteRepository.findById(id).orElse(null), id);
    }

    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    public void update(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }
}
