package com.screencap.dictionary.security;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.screencap.dictionary.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class ApplicationUserDetailServices implements UserDetailsService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationUserDao applicationUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // List<User> users = getUsers();

        // User theUser = users.stream().filter(user ->
        // user.getUsername().equals(username)).findAny().get();

        User theUser = applicationUserDao.getUserByUserName(username);
        String name = theUser.getUsername();
        String pw = theUser.getPassword();

        return new org.springframework.security.core.userdetails.User(name, pw, new ArrayList<GrantedAuthority>());
    };



    // private List<User> getUsers() {



    // List<User> users = Arrays.asList(
    // new User("james", passwordEncoder.encode("123")),
    // new User("peter", passwordEncoder.encode("321")),
    // new User("john", passwordEncoder.encode("111")),
    // new User("mike", passwordEncoder.encode("222"))
    // );
    // return users;
    // }
}
