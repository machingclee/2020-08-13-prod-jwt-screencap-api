package com.screencap.dictionary.security;

import java.util.List;
import com.screencap.dictionary.models.User;

public interface IApplicationUserDao {
    public List<User> getUsers();

    public User getUserByUserName(String username);

    public void saveUser(User user);
}
