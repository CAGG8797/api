package com.example.api.services;

import com.example.api.dto.PostDTO;
import com.example.api.models.Post;
import com.example.api.models.User;

public interface IUserService {

    public User getUser(Integer id);

    public PostDTO createPost(Post post, User user);

    public void followUser(Integer idUser, Integer idFollower);

}
