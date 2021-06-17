package com.example.api.services;

import com.example.api.models.User;
import com.example.api.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    @Override
    public void followUser(Integer idUser, Integer idFollower) {

        if (idUser == null || idFollower == null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ids can't be null");

        if (idUser == idFollower)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Follower and user can't be same person");

        Optional<User> user = usersRepository.findById(idUser);

        if ( !user.isPresent() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

        Optional<User> follower = usersRepository.findById(idFollower);

        if ( !follower.isPresent() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Follower not found");

        if ( user.get().existFollower(follower.get()) )
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The follower already follows the user.");

        user.get().addFollower(follower.get());

    }
}
