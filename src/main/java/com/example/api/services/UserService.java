package com.example.api.services;

import com.example.api.dto.PostDTO;
import com.example.api.models.Post;
import com.example.api.models.User;
import com.example.api.repositories.PostRepository;
import com.example.api.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public User getUser(Integer id) {

        Optional<User> user = usersRepository.findById(id);

        if ( !user.isPresent() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

        return user.get();

    }

    @Override
    public User getUser(String username) {

        Optional<User> user = usersRepository.findByUsername(username);

        if ( !user.isPresent() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

        return user.get();

    }

    @Transactional
    @Override
    public PostDTO createPost(Post post, User user) {

        Optional<User> userOptional = null;

        if ( post.getContent() == null || post.getContent().isEmpty() )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post must have content");

         userOptional = (user.getId() != null) ? usersRepository.findById(user.getId()) : Optional.empty();

        if ( !userOptional.isPresent() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

        user = userOptional.get();

        post.setUser(user);

        post = postRepository.save(post);

        return new PostDTO(post.getId(), post.getContent(),post.getCreationDate(),
                post.getUser().getId(), post.getUser().getName());

    }

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

    @Override
    public List<PostDTO> getPostFollowed(User user, Pageable pageable) {

        List<Post> posts = postRepository.getPosts(user, pageable);

        return posts
                .stream()
                .map(post -> new PostDTO(post.getId(), post.getContent(),post.getCreationDate(), post.getUser().getId(), post.getUser().getName()))
                .collect(Collectors.toList());

    }

}
