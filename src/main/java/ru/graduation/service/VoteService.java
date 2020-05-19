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
        Vote vote = voteRepository.findByUserIdAndDate(userId, LocalDate.now());
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

    public int delete(int voteId, int userId) {
        return checkNotFound(voteRepository.delete(voteId, userId), "");
    }

    public Vote getForUserAndDate(int userId, LocalDate date){
        return voteRepository.findByUserIdAndDate(userId, date);
    }

    public List<Vote> getAllForUser(int userId) {
        return voteRepository.getAllByUser(userId);
    }

    public List<Vote> getAllForDate(LocalDate date){
        if (date == null){
            date = LocalDate.now();
        }
        return voteRepository.findByDate(date);
    }

    public List<Vote> getAllForRestaurant(int restaurantId) {
        return voteRepository.getAllByRestaurant(restaurantId);
    }
}
