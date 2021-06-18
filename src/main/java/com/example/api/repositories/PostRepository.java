package com.example.api.repositories;

import com.example.api.models.Post;
import com.example.api.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

    @Query("select distinct p from Post p left join fetch p.user u left join fetch u.followers f " +
            "where f = ?1")
    List<Post> getPosts(User user, Pageable pageable);

}
