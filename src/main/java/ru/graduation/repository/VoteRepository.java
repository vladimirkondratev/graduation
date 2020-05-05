package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Vote;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("")
        //TODO
    Vote save(Vote vote, int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:voteId AND v.user.id=:userId")
    int delete(@Param("voteId") int voteId, @Param("userId") int userId);

    @Query("SELECT Vote FROM Vote v WHERE v.id=:voteId AND v.user.id=:userId")
    Vote get(@Param("voteId") int dishId, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId")
    List<Vote> getAll(@Param("userId") int userId);
}
