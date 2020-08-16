package com.screencap.dictionary.interfaces;

import java.util.List;
import com.screencap.dictionary.models.entities.User;


public interface IApplicationUserDao {
    public List<User> getUsers();

    public User getUserByUserName(String username);

    public void saveUser(User user);
}
