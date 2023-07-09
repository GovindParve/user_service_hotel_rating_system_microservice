package com.in.user.services;

import com.in.user.entities.User;

import java.util.List;

public interface UserService {

    //create
    public User saveUser(User user);

    //get all user
    public List<User> getAllUser();

    //get single user of given userId
    public User getUser(String userId);



}
