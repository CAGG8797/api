package com.example.api.repositories;

import com.example.api.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<User,Integer> {

    Optional<User> findByUsername(String username);

}
