package com.erikalves.application.service;


import com.erikalves.application.model.Product;
import com.erikalves.application.model.User;

import java.util.List;


public interface UserService extends GenericService <User,Long>{


    public User findUserByEmail(String email);

    public User findUserByName(String name);
}
