package com.philspelman.javabelt_two.repositories;

import com.philspelman.javabelt_two.models.Rating;
import com.philspelman.javabelt_two.models.RatingIdentity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating, RatingIdentity> {
}