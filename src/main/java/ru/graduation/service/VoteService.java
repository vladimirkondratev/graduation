package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.model.Vote;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;
import ru.graduation.repository.VoteRepository;
import ru.graduation.util.DeadLineTime;

import java.time.*;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    private Clock clock;

    private ZoneId zoneId;

    public VoteService(VoteRepository voteRepository,
                       UserRepository userRepository,
                       RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.clock = Clock.systemDefaultZone();
        this.zoneId = ZoneId.systemDefault();
    }

    /**
     * for test purpose only, to test voting before and after deadLine.
     * Should be change to aspect.
     *
     * @param dateTime
     */
    public void setClockAndTimeZone(LocalDateTime dateTime) {
        this.clock = Clock.fixed(dateTime.atZone(zoneId).toInstant(), zoneId);
    }

    public Vote doVote(Integer userId, int restaurantId) {
        Assert.notNull(userId, "userId must not be null");
        return doVote(userId, restaurantId, LocalDate.now(clock), LocalTime.now(clock));
    }

    @Transactional
    public Vote doVote(int userId, int restaurantId, LocalDate date, LocalTime time) {
        Vote vote = voteRepository.findByUserIdAndDate(userId, date);
        User user = userRepository.getOne(userId);
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        if (vote == null) {
            return voteRepository.save(new Vote(null, date, user, restaurant));
        } else {
            if (time.isBefore(DeadLineTime.deadLine)) {
                vote.setRestaurant(restaurant);
                return voteRepository.save(vote);
            } else {
                return null;
            }
        }
    }
}
