package com.example.api.services;

import com.example.api.dto.PostDTO;
import com.example.api.models.Post;
import com.example.api.models.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    public User getUser(Integer id);

    public User getUser(String username);

    public PostDTO createPost(Post post, User user);

    public void followUser(Integer idUser, Integer idFollower);

    public List<PostDTO> getPostFollowed(User user, Pageable pageable);

}
