package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.model.Vote;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;
import ru.graduation.repository.VoteRepository;
import ru.graduation.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @Transactional
    public Vote doVote(int userId, int restaurantId){
        Vote vote = voteRepository.getForUserAndDate(userId, LocalDate.now());
        User user = userRepository.getOne(userId);
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        if (vote == null){
            return voteRepository.save(new Vote(null, LocalDate.now(), user, restaurant));
        } else {
            if (LocalTime.now().isBefore(DateTimeUtil.deadLine)){
                vote.setRestaurant(restaurant);
                return voteRepository.save(vote);
            } else {
                return null;
            }
        }
    }

//    @Transactional
//    public Vote create(Vote vote, int userId, int restaurantId) {
//        Assert.notNull(vote, "vote must not be null");
//        User user = userRepository.getOne(userId);
//        vote.setUser(user);
//        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
//        vote.setRestaurant(restaurant);
//        return voteRepository.save(vote);
//    }
//
//    @Transactional
//    public void update(Vote vote, int userId) {
//        Assert.notNull(vote, "vote must not be null");
//        int voteId = vote.getId();
////        checkNotFoundWithId(voteRepository.get(voteId, userId), voteId);
//        checkNotFoundWithId(voteRepository.save(vote), voteId);
//    }

    public int delete(int voteId, int userId) {
        return checkNotFound(voteRepository.delete(voteId, userId), "");
    }

    public Vote get(int voteId, int userId) {
        return checkNotFoundWithId(voteRepository.get(voteId, userId), voteId);
    }

    public Vote getForUserAndDate(int userId, LocalDate day){
        return voteRepository.getForUserAndDate(userId, day);
    }

    public List<Vote> getAllForUser(int userId) {
        return voteRepository.getAllByUser(userId);
    }

    public List<Vote> getAllForRestaurant(int restaurantId) {
        return voteRepository.getAllByRestaurant(restaurantId);
    }
}
