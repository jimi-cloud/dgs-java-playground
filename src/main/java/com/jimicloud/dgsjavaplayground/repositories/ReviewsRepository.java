package com.jimicloud.dgsjavaplayground.repositories;

import com.jimicloud.dgsjavaplayground.models.Review;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ReviewsRepository extends Repository<Review, String> {
    List<Review> findByShowId(String showId);
}
